package com.example.learn_jwt.controller.user

import com.example.learn_jwt.model.Role
import com.example.learn_jwt.model.User
import com.example.learn_jwt.service.UserService
import java.util.UUID
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {
    @GetMapping
    fun listAll() = userService.findAll().map { it.toUserResponse() }

    @GetMapping("/{uuid}")
    fun findByUUD(@PathVariable uuid: UUID) = userService.findByUUID(uuid)?.toUserResponse()
        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Can not find a user.")

    @DeleteMapping("/{uuid}")
    fun deleteByUUID(@PathVariable uuid: UUID): ResponseEntity<Boolean> {
        val success = userService.deleteByUUId(uuid)
        return if (success) ResponseEntity.noContent().build()
        else throw ResponseStatusException(HttpStatus.NOT_FOUND, "Can not delete user.")
    }

    @PostMapping
    fun create(@RequestBody user: UserRequest) : UserResponse =
        userService.createUser(user.toModel())?.toUserResponse()
        ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not create a user.")

}

fun User.toUserResponse() = UserResponse(id = this.id, email = this.email)
private fun UserRequest.toModel() = User(
    id = UUID.randomUUID(),
    email = this.email,
    password = this.password,
    role = Role.USER
)