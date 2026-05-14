package com.sadiq.arogyasahayalocal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadiq.arogyasahayalocal.data.HealthCampEntity
import com.sadiq.arogyasahayalocal.data.repository.HealthCampRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HealthCampViewModel(
    private val repository: HealthCampRepository
) : ViewModel() {

    /*
    --------------------------------
    ALL CAMPS FLOW
    --------------------------------
    */

    val camps = repository.getAllCamps()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /*
    --------------------------------
    REGISTER CAMP
    --------------------------------
    */

    fun registerCamp(
        title: String,
        date: String,
        location: String,
        workerName: String,
        campType: String
    ) {

        val camp = HealthCampEntity(
            title = title,
            date = date,
            location = location,
            workerName = workerName,
            campType = campType,
            isRegistered = true
        )

        viewModelScope.launch {
            repository.insertCamp(camp)
        }
    }

    /*
    --------------------------------
    UPDATE CAMP
    --------------------------------
    */

    fun updateCamp(
        camp: HealthCampEntity
    ) {
        viewModelScope.launch {
            repository.updateCamp(camp)
        }
    }
}