package com.example.login.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.login.model.Usuario

@Dao
interface UserDao {
    @Query("SELECT * FROM Usuario")
    fun getUser(): Usuario

    @Insert
    fun insertAll(vararg users: Usuario)

    @Delete
    fun delete(user: Usuario)
}