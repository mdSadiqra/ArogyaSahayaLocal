package com.sadiq.arogyasahayalocal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sadiq.arogyasahayalocal.data.repository.HealthCampRepository

class HealthCampViewModelFactory(
    private val repository: HealthCampRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                HealthCampViewModel::class.java
            )
        ) {
            return HealthCampViewModel(
                repository
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}