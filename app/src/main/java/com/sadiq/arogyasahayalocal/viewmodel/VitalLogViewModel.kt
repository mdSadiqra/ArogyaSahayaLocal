package com.sadiq.arogyasahayalocal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadiq.arogyasahayalocal.data.VitalLogEntity
import com.sadiq.arogyasahayalocal.data.repository.VitalLogRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VitalLogViewModel(
    private val repository: VitalLogRepository
) : ViewModel() {

    val vitalLogs = repository.getAllVitalLogs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addVitalLog(
        systolicBP: Int,
        diastolicBP: Int,
        heartRate: Int,
        glucoseLevel: Int,
        date: String
    ) {
        val vitalLog = VitalLogEntity(
            systolicBP = systolicBP,
            diastolicBP = diastolicBP,
            heartRate = heartRate,
            glucoseLevel = glucoseLevel,
            date = date
        )

        viewModelScope.launch {
            repository.insertVitalLog(vitalLog)
        }
    }
}