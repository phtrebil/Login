package com.example.login.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.login.database.AppDatabase
import com.example.login.databinding.ActivityCadastroBinding
import com.example.login.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroActivity : AppCompatActivity() {

    // Inicializa o binding usando lazy para inflar o layout
    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    // Inicializa o banco de dados usando lazy para evitar a inicialização desnecessária
    private val dataBase by lazy {
        AppDatabase.instancia(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Configura o botão de cadastro para chamar a função salvaUsuario()
        configuraBotaoDeCadastro()
    }

    private fun configuraBotaoDeCadastro() {
        binding.buttonCadastrar.setOnClickListener {
            salvaUsuario()
        }
    }

    private fun salvaUsuario() {
        val nome = binding.editTextNome.text.toString()
        val user = binding.editTextNomeUsuario.text.toString()
        val senha = binding.editTextSenha.text.toString()
        val email = binding.editTextEmail.text.toString()


        // Inicia uma CoroutineScope para executar a tarefa assíncrona
        lifecycleScope.launch {
            // Acessa o banco de dados em uma thread de E/S para buscar o usuário
            val usuarioExistente = withContext(Dispatchers.IO) {
                dataBase.getUser(user)
            }

            // Verifica se o usuário já existe
            if (usuarioExistente != null) {
                // Exibe uma mensagem de erro na thread principal caso o usuário já exista
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@CadastroActivity, "Usuário já existe", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                // Cria um novo objeto de usuário
                val novoUsuario = Usuario(
                    nome = nome, usuario = user, senha = senha, email = email
                )

                // Insere o novo usuário no banco de dados em uma thread de E/S
                withContext(Dispatchers.IO) {
                    dataBase.insertAll(novoUsuario)
                }

                // Redireciona para a tela de login apenas se o usuário for criado com sucesso
                iniciarTelaLogin()
            }
        }
    }
    private fun iniciarTelaLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Finaliza a atividade atual para que o usuário não possa voltar para a tela de cadastro pressionando o botão Voltar
    }
}
