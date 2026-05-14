package com.sadiq.arogyasahayalocal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadiq.arogyasahayalocal.data.UserEntity
import com.sadiq.arogyasahayalocal.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    // Login State
    private val _loginState = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    // Update Password State
    private val _updatePasswordState = MutableStateFlow("")
    val updatePasswordState: StateFlow<String> = _updatePasswordState

    // Register User
    fun registerUser(
        fullName: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {

            val existingUser =
                repository.checkUserExists(email)

            if (existingUser == null) {

                repository.registerUser(
                    UserEntity(
                        fullName = fullName,
                        email = email,
                        password = password
                    )
                )
            }
        }
    }

    // Login User
    fun loginUser(
        email: String,
        password: String
    ) {
        viewModelScope.launch {

            val user = repository.loginUser(
                email = email,
                password = password
            )

            _loginState.value = user != null
        }
    }

    // Update Password
    fun updatePassword(
        email: String,
        newPassword: String
    ) {
        viewModelScope.launch {

            repository.updatePassword(
                email = email,
                newPassword = newPassword
            )

            _updatePasswordState.value =
                "Password Updated Successfully"
        }
    }
}