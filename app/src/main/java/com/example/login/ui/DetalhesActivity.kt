package com.example.login.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import com.example.login.database.AppDatabase
import com.example.login.databinding.ActivityDetalhesBinding
import com.example.login.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalhesActivity : AppCompatActivity() {

    // Inicialização tardia do objeto binding usando a propriedade by lazy
    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }

    // Inicialização tardia do objeto do banco de dados usando a propriedade by lazy
    private val dataBase by lazy {
        AppDatabase.instancia(this).userDao()
    }

    private var user: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recebeInformacaoDeLogin()

        configuraBotaoLogOff()
    }

    private fun configuraBotaoLogOff() {
        binding.logoff.setOnClickListener {
            realizarLogoff()
        }
    }

    private fun realizarLogoff() {
        // Atualiza o estado de login para falso
        val loginActivityIntent = Intent(this, LoginActivity::class.java)
        loginActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        loginActivityIntent.putExtra("logoff", true)
        startActivity(loginActivityIntent)
        finish()
    }

    private fun recebeInformacaoDeLogin() {
        // Obtém uma instância do SharedPreferences padrão
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        // Obtém o usuário salvo no SharedPreferences com a chave "user"
        val usuarioRecebido = sharedPreferences.getString("user", null)

        // Verifica se o usuário recebido não está vazio ou nulo
        if (!usuarioRecebido.isNullOrEmpty()) {
            // Inicia uma CoroutineScope na thread IO
            CoroutineScope(Dispatchers.IO).launch {
                // Acessa o banco de dados para obter informações do usuário usando a função getUser()
                user = withContext(Dispatchers.IO) {
                    dataBase.getUser(usuarioRecebido)
                }

                // Volta para a thread principal para exibir as informações
                withContext(Dispatchers.Main) {
                    mostraInformacao()
                }
            }
        }
    }

    // Função para exibir as informações do usuário na interface
    private fun mostraInformacao() {
        binding.textViewName.text = user?.nome
        binding.textViewUsername.text = user?.usuario
        binding.textViewEmail.text = user?.email
        binding.textViewPassword.text = user?.senha
    }
}
