package com.woke.desafio.woke.backend.controller

import com.woke.desafio.woke.backend.dto.LoginDto
import com.woke.desafio.woke.backend.dto.Message
import com.woke.desafio.woke.backend.dto.UserDto
import com.woke.desafio.woke.backend.dto.UserView
import com.woke.desafio.woke.backend.service.impl.UserService
import io.jsonwebtoken.Jwts
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@RestController
@RequestMapping("/v1/api/auth")
class AuthController(
    private val userService: UserService,
) {

    val secretKey: SecretKey = generateSecretKey()

    fun generateSecretKey(): SecretKey {
        val keyBytes = ByteArray(64) // 64 bytes = 512 bits
        SecureRandom().nextBytes(keyBytes)
        return SecretKeySpec(keyBytes, "HmacSHA512")
    }

    @PostMapping("/register")
    fun saveUser(@RequestBody userDto: UserDto): ResponseEntity<String> {
        val savedUser = this.userService.save(userDto.toEntity())
        return ResponseEntity.ok("User ${savedUser.email} saved!")
    }

    @PostMapping("/login")
    fun login(@RequestBody login: LoginDto, response: HttpServletResponse): ResponseEntity<Any> {
            val user = this.userService.findByEmail(login)
                ?: return ResponseEntity.badRequest().body(Message("User not found"))

            if(!user.comparePassword(login.password)){
                return ResponseEntity.badRequest().body(Message("invalid Password"))
            }

            val issuer = user.id.toString()

            val jwt = Jwts.builder()
                .issuer(issuer)
                .expiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
                .signWith(secretKey).compact()

            var cookie = Cookie("jwt", jwt)
            cookie.isHttpOnly = true

            response.addCookie(cookie)

            return ResponseEntity.ok(Message("Logado com sucesso"))
    }

    @GetMapping("/authenticate")
    fun auth(@CookieValue("jwt") jwt: String?): ResponseEntity<Any>{
        try {
            if(jwt == null){
                return ResponseEntity.status(401).body(Message("Unauthorized"))
            }

            val body = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .payload

            val user = userService.findById(UUID.fromString(body.issuer))

            return ResponseEntity.ok(user)
        }
        catch (ex: Exception){
            return ResponseEntity.status(401).body(Message("Unauthorized"))
        }


    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any>{
        var cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.ok(Message("Até Logo"))
    }
}