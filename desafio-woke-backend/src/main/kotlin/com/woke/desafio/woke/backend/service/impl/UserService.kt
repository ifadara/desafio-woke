package com.woke.desafio.woke.backend.service.impl

import com.woke.desafio.woke.backend.dto.LoginDto
import com.woke.desafio.woke.backend.dto.UserView
import com.woke.desafio.woke.backend.entity.User
import com.woke.desafio.woke.backend.repository.UserRepository
import com.woke.desafio.woke.backend.service.IUserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
): IUserService {


    override fun save(user: User): User =
        this.userRepository.save(user)


    override fun findById(id: UUID): User =
        this.userRepository.findById(id).orElseThrow {
            throw RuntimeException("Id $id not found")
        }

    override fun delete(id: UUID) = this.userRepository.deleteById(id)

    override fun apply(id: UUID): UserView {
        val user: User = findById(id)
        return UserView(user)
    }


    override fun findByEmail(loginDto: LoginDto): User? {
        return this.userRepository.findByEmail(loginDto.email)
    }


}