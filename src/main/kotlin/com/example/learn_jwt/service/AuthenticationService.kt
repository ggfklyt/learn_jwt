package com.example.learn_jwt.service

import com.example.learn_jwt.config.JwtProperties
import com.example.learn_jwt.controller.auth.AuthenticationRequest
import com.example.learn_jwt.controller.auth.AuthenticationResponse
import com.example.learn_jwt.repository.RefreshTokenRepository
import java.util.Date
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)
        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)

        refreshTokenRepository.save(refreshToken, user)
        return AuthenticationResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

   private fun generateToken(user: UserDetails, expirationTime: Long) = tokenService.generate(
       userDetails = user,
       expirationDate = Date(System.currentTimeMillis() + expirationTime)
   )

    private fun generateAccessToken(user: UserDetails) = generateToken(
        user = user,
        expirationTime = jwtProperties.accessTokenExpiration
    )

    private fun generateRefreshToken(user: UserDetails) = generateToken(
        user = user,
        expirationTime = jwtProperties.refreshTokenExpiration
    )

    fun refreshAccessToken(token: String): String? {
        val extractedEmail = tokenService.extractEmail(token)

        return extractedEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(token)

            if (!tokenService.isExpired(token) && currentUserDetails.username == refreshTokenUserDetails?.username)
                generateAccessToken(currentUserDetails)
            else null
        }
    }

}
