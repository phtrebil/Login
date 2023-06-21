package com.example.login.model

import androidx.room.Entity

@Entity
class Usuario(
    private val usuario: String,
    private val nome: String,
    private val email: String,
    private val senha: String
) {
}