package com.example.learn_jwt.service

import com.example.learn_jwt.model.User
import com.example.learn_jwt.repository.UserRepository
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun createUser(user: User) =
        if (userRepository.findByEmail(user.email) == null) {
            userRepository.save(user)
            user
        } else {
            null
        }

    fun findByUUID(uuid: UUID) = userRepository.findByUUID(uuid)
    fun findAll() = userRepository.findAll()
    fun deleteByUUId(uuid: UUID) = userRepository.deleteByUUID(uuid)
}