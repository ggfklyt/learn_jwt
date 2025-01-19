package com.example.learn_jwt.model

import java.util.UUID

data class User(
    val id: UUID,
    val password: String,
    val email: String,
    val role: Role
)