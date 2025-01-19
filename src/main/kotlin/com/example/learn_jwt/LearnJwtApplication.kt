package com.example.learn_jwt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
class LearnJwtApplication

fun main(args: Array<String>) {
    runApplication<LearnJwtApplication>(*args)
}
