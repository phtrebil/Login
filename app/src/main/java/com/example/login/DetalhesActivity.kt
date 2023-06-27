package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login.database.AppDatabase
import com.example.login.databinding.ActivityDetalhesBinding
import com.example.login.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalhesActivity : AppCompatActivity() {

    // Inicialização tardia do objeto binding usando a propriedade by lazy
    val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }

    // Inicialização tardia do objeto do banco de dados usando a propriedade by lazy
    val dataBase by lazy {
        AppDatabase.instancia(this).userDao()
    }

    var user: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val usuarioRecebido = intent
        if(usuarioRecebido.hasExtra("user")){
            intent.getStringExtra("user")?.let {
                // Início da corrotina para buscar o usuário do banco de dados
                CoroutineScope(Dispatchers.IO).launch {
                    user = withContext(Dispatchers.IO) {
                        dataBase.getUser(it)
                    }
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
