package com.example.learn_jwt.service

import com.example.learn_jwt.repository.ArticleRepository
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) {
    fun findAll() = articleRepository.findAll()
}