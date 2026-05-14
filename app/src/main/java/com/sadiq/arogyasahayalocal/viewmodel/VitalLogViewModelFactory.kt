package com.sadiq.arogyasahayalocal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sadiq.arogyasahayalocal.data.repository.VitalLogRepository

class VitalLogViewModelFactory(
    private val repository: VitalLogRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VitalLogViewModel(repository) as T
    }
}