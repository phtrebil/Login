package com.example.login.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.login.database.dao.UserDao
import com.example.login.model.Usuario

@Database(
    version = 1,
    entities = [Usuario::class]
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        // A palavra-chave volatile garante que as alterações feitas por uma thread sejam visíveis imediatamente para outras threads.
        private var db: AppDatabase? =null
        // Função para obter uma instância do banco de dados
        fun instancia(context: Context): AppDatabase{
            // Verifica se o banco de dados já foi instanciado anteriormente, caso positivo retorna o banco instanciado, em caso negativo cria um db.
            return db?:Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user.db"
            ).build()
        }
    }

}