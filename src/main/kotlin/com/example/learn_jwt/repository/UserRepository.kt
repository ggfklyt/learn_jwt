package com.example.learn_jwt.repository

import com.example.learn_jwt.model.Role
import com.example.learn_jwt.model.User
import java.util.UUID
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val encoder: PasswordEncoder) {
    private val users = mutableListOf(
        User(id = UUID.randomUUID(), password = encoder.encode("pass1"), email = "email1@gmail.com", role = Role.USER),
        User(id = UUID.randomUUID(), password = encoder.encode("pass2"), email = "email2@gmail.com", role = Role.ADMIN),
        User(id = UUID.randomUUID(), password = encoder.encode("pass3"), email = "email3@gmail.com", role = Role.USER)
    )

    fun save(user: User) = users.add(user.copy(password = encoder.encode(user.password)))
    fun findAll(): List<User> = users
    fun findByEmail(email: String) = users.find { it.email == email }
    fun findByUUID(uuid: UUID) = users.find { it.id == uuid }
    fun deleteByUUID(uuid: UUID) = findByUUID(uuid)?.let {
        users.remove(it)
    } ?: false
}