package com.example.login.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Usuario(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val usuario: String,
    val nome: String,
    val email: String,
    val senha: String
)