package com.sadiq.arogyasahayalocal.data.repository

import com.sadiq.arogyasahayalocal.data.UserEntity
import com.sadiq.arogyasahayalocal.data.local.UserDao

class UserRepository(
    private val userDao: UserDao
) {

    // Register User
    suspend fun registerUser(
        user: UserEntity
    ) {
        userDao.registerUser(user)
    }

    // Login User
    suspend fun loginUser(
        email: String,
        password: String
    ): UserEntity? {
        return userDao.loginUser(
            email,
            password
        )
    }

    // Check Existing User
    suspend fun checkUserExists(
        email: String
    ): UserEntity? {
        return userDao.checkUserExists(
            email
        )
    }

    // Update Password
    suspend fun updatePassword(
        email: String,
        newPassword: String
    ) {
        userDao.updatePassword(
            email,
            newPassword
        )
    }
}