package com.example.learn_jwt.controller.user

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val email: String
)