package com.woke.desafio.woke.backend.dto

import com.woke.desafio.woke.backend.entity.User
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.Date

data class UserDto(
    val name: String,
    val telephone: String,
    val email: String,
    val password: String,
    val birthDate: String
) {

    fun toEntity(): User {
        val user = User()
        user.name = this.name
        user.telephone = this.telephone
        user.email = this.email
        user.password = this.password
        user.birthDate = LocalDate.parse(birthDate)
        return user
    }
}