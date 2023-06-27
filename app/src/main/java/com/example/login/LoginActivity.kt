package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.database.AppDatabase
import com.example.login.databinding.ActivityMainBinding
import com.example.login.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    // Inicializa o binding usando lazy para inflar o layout
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // Inicializa o banco de dados usando lazy para evitar a inicialização desnecessária
    private val dataBase by lazy {
        AppDatabase.instancia(this).userDao()
    }

    private var usuarioEncontrado: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Configura o texto para redirecionar para a tela de cadastro
        configuraTextoNaoTemCadastro()

        // Configura o botão de entrar para validar a senha
        configuraBotaoEntrar()
    }

    private fun configuraBotaoEntrar() {
        binding.buttonEntrar.setOnClickListener {
            validaSenha()
        }
    }

    private fun validaSenha() {
        val user = binding.editTextLogin.text.toString()
        val senha = binding.editTextSenha.text.toString()

        // Inicia uma CoroutineScope na thread principal para executar a tarefa assíncrona
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // Acessa o banco de dados em uma thread de E/S para buscar o usuário
                usuarioEncontrado = withContext(Dispatchers.IO) {
                    dataBase.getUser(user)
                }

                // Verifica se o usuário foi encontrado e a senha está correta
                if (usuarioEncontrado != null && usuarioEncontrado!!.senha == senha) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login efetuado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    vaiParaDetalhes()
                } else {
                    // Lança uma exceção caso o login ou senha estejam incorretos
                    throw Exception("Login ou senha incorretos")
                }
            } catch (e: Exception) {
                // Captura a exceção e exibe uma mensagem de erro
                Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun vaiParaDetalhes() {
        val intent = Intent(this, DetalhesActivity::class.java).apply {
            putExtra("user",usuarioEncontrado?.usuario)
        }
        startActivity(intent)
    }

    private fun configuraTextoNaoTemCadastro() {
        binding.textViewNaoCadastrado.setOnClickListener {
            // Redireciona para a tela de cadastro ao clicar no texto
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}



