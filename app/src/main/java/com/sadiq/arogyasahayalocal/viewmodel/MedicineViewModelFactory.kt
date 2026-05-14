package com.sadiq.arogyasahayalocal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sadiq.arogyasahayalocal.data.repository.MedicineRepository

class MedicineViewModelFactory(
    private val repository: MedicineRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MedicineViewModel(repository) as T
    }
}