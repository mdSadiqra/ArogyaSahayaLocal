package com.sadiq.arogyasahayalocal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sadiq.arogyasahayalocal.data.UserEntity

@Dao
interface UserDao {

    // Register New User
    @Insert
    suspend fun registerUser(
        user: UserEntity
    )

    // Login User
    @Query(
        "SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1"
    )
    suspend fun loginUser(
        email: String,
        password: String
    ): UserEntity?

    // Check Existing User
    @Query(
        "SELECT * FROM users WHERE email = :email LIMIT 1"
    )
    suspend fun checkUserExists(
        email: String
    ): UserEntity?

    // Update Password
    @Query(
        "UPDATE users SET password = :newPassword WHERE email = :email"
    )
    suspend fun updatePassword(
        email: String,
        newPassword: String
    )
}