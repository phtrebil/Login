package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.login.database.AppDatabase
import com.example.login.databinding.ActivityCadastroBinding
import com.example.login.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val dataBase by lazy {
        AppDatabase.instancia(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configuraBotaoDeCadastro()


    }

    private fun configuraBotaoDeCadastro() {
        binding.buttonCadastrar.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                dataBase.insertAll(salvaUsuario())
            }
            finish()
        }
    }

    private fun salvaUsuario(): Usuario{
        val nome = binding.editTextNome.text.toString()
        val user = binding.editTextNomeUsuario.text.toString()
        val senha = binding.editTextSenha.text.toString()
        val email = binding.editTextEmail.text.toString()

        return Usuario(
            nome = nome,
            usuario = user,
            email = email,
            senha = senha
        )


    }
}