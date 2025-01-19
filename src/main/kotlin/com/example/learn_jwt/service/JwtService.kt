//package com.example.learn_jwt.service
//
//import io.jsonwebtoken.Claims
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Service
//
//@Service
//class JwtService(
//    @Value("\${token.signing.key}")
//    private val jwtSigningKey: String
//) {
//    fun <T: Any> extractClaim(token:  String, claimsResolvers: Function<Claims, T>): T {
//
//    }
//
//    fun extractUserName(token: String) = extractClaim(token, Claims::getSubject)
//}
//
//
