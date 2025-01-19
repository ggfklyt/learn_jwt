package com.example.learn_jwt.repository

import com.example.learn_jwt.model.Article
import java.util.UUID
import org.springframework.stereotype.Repository

@Repository
class ArticleRepository {
    private val articles = listOf(
        Article(id = UUID.randomUUID(), title = "Article 1", content = "Content 1"),
        Article(id = UUID.randomUUID(), title = "Article 2", content = "Content 2")
    )

    fun findAll() = articles
}