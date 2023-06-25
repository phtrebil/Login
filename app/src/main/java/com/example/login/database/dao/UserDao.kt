package com.example.login.database.dao

import androidx.room.*
import com.example.login.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query ("SELECT * FROM Usuario WHERE usuario = :user")
    fun getUser(user: String?): Usuario?

    @Insert
    fun insertAll(vararg users: Usuario)

}