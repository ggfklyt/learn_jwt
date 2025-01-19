package com.example.learn_jwt.controller.article

import com.example.learn_jwt.model.Article
import com.example.learn_jwt.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/article")
class ArticleController(
    private val articleService: ArticleService
) {
    @GetMapping
    fun listAll(): List<ArticleResponse> {
        return articleService.findAll().map { it.toResponse() }
    }

    fun Article.toResponse() = ArticleResponse(
        id = this.id,
        title = this.title,
        content = this.content
    )
}