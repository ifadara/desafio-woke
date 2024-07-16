package com.woke.desafio.woke.backend.service

import com.woke.desafio.woke.backend.dto.LoginDto
import com.woke.desafio.woke.backend.dto.UserView
import com.woke.desafio.woke.backend.entity.User
import java.util.UUID

interface IUserService {

    fun save(user: User): User

    fun findById(id: UUID): User

    fun delete(id: UUID)

    fun apply(id: UUID): UserView

    fun findByEmail(loginDto: LoginDto): User?
}