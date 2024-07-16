package com.woke.desafio.woke.backend.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "Usuario")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    lateinit var id: UUID
    @Column(nullable = false)
    var name: String = ""
    @Column(nullable = false)
    var telephone: String = ""
    @Column(nullable = false, unique = true)
    var email: String = ""
    @Column(nullable = false)
    @JsonIgnore
    var password: String = ""
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }

    @Column(nullable = false)
    @DateTimeFormat(style = "yyyy-MM-dd")
    lateinit var birthDate: LocalDate

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }

}