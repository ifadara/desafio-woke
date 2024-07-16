package com.woke.desafio.woke.backend.dto

import com.woke.desafio.woke.backend.entity.User
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.util.Date

class UserView(
    val name: String,
    val telephone: String,
    val email: String,
    @DateTimeFormat(style = "yyyy-MM-dd")
    val birthDate: LocalDate?
) {
    constructor(user: User?): this (
        name = user?.name.toString(),
        telephone = user?.telephone.toString(),
        email = user?.email.toString(),
        birthDate = user?.birthDate
        )
}