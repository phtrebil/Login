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
        private var db: AppDatabase? =null

        fun instancia(context: Context): AppDatabase{
            return db?:Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user.db"
            ).build()
        }
    }

}