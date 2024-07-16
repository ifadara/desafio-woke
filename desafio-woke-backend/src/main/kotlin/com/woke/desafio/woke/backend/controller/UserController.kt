package com.woke.desafio.woke.backend.controller

import com.woke.desafio.woke.backend.dto.UserDto
import com.woke.desafio.woke.backend.dto.UserView
import com.woke.desafio.woke.backend.entity.User
import com.woke.desafio.woke.backend.service.impl.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {


    @PostMapping("/{id}")
    fun apply(@PathVariable id: UUID): ResponseEntity<UserView> {
        val userView: UserView = this.userService.apply(id)
        return ResponseEntity.status(HttpStatus.CREATED).body(userView)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): ResponseEntity<UserView> {
        val user: User = this.userService.findById(id)
        return ResponseEntity.status(HttpStatus.CREATED).body(UserView(user))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: UUID) = this.userService.delete(id)

}