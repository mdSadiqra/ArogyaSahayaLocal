package com.sadiq.arogyasahayalocal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadiq.arogyasahayalocal.data.MedicineEntity
import com.sadiq.arogyasahayalocal.data.repository.MedicineRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MedicineViewModel(
    private val repository: MedicineRepository
) : ViewModel() {

    /*
    --------------------------------
    ALL MEDICINES FLOW
    --------------------------------
    */

    val medicines = repository.getAllMedicines()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /*
    --------------------------------
    ADD MEDICINE
    --------------------------------
    */

    fun addMedicine(
        medicineName: String,
        dosage: String,
        morning: Boolean,
        afternoon: Boolean,
        night: Boolean,
        reminderHour: String,
        reminderMinute: String,
        reminderPeriod: String
    ) {

        val medicine = MedicineEntity(
            medicineName = medicineName,
            dosage = dosage,
            morning = morning,
            afternoon = afternoon,
            night = night,
            reminderHour = reminderHour,
            reminderMinute = reminderMinute,
            reminderPeriod = reminderPeriod
        )

        viewModelScope.launch {
            repository.insertMedicine(medicine)
        }
    }

    /*
    --------------------------------
    DELETE MEDICINE
    --------------------------------
    */

    fun deleteMedicine(
        medicine: MedicineEntity
    ) {
        viewModelScope.launch {
            repository.deleteMedicine(medicine)
        }
    }

    /*
    --------------------------------
    UPDATE MEDICINE
    --------------------------------
    */

    fun updateMedicine(
        medicine: MedicineEntity
    ) {
        viewModelScope.launch {
            repository.updateMedicine(medicine)
        }
    }
}