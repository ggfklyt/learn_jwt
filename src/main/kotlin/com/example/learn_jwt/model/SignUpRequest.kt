package com.example.learn_jwt.model

data class SignUpRequest(
    private val username: String,
    private val email: String,
    private val password: String
)