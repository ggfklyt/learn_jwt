package com.example.learn_jwt.model

data class SignInRequest(
    private val username: String,
    private val password: String
)