package com.sadiq.arogyasahayalocal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadiq.arogyasahayalocal.data.ProfileEntity
import com.sadiq.arogyasahayalocal.data.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _profileState =
        MutableStateFlow<ProfileEntity?>(null)

    val profileState: StateFlow<ProfileEntity?> =
        _profileState

    fun saveProfile(
        fullName: String,
        age: String,
        bloodGroup: String,
        chronicCondition: String,
        emergencyContact: String,
        address: String
    ) {
        viewModelScope.launch {

            repository.saveProfile(
                ProfileEntity(
                    fullName = fullName,
                    age = age,
                    bloodGroup = bloodGroup,
                    chronicCondition = chronicCondition,
                    emergencyContact = emergencyContact,
                    address = address
                )
            )

            loadProfile()
        }
    }

    fun loadProfile() {
        viewModelScope.launch {
            _profileState.value =
                repository.getProfile()
        }
    }
}