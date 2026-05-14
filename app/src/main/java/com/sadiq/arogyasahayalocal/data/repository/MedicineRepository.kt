package com.sadiq.arogyasahayalocal.data.repository

import com.sadiq.arogyasahayalocal.data.MedicineEntity
import com.sadiq.arogyasahayalocal.data.local.MedicineDao
import kotlinx.coroutines.flow.Flow

class MedicineRepository(
    private val medicineDao: MedicineDao
) {

    suspend fun insertMedicine(
        medicine: MedicineEntity
    ) {
        medicineDao.insertMedicine(medicine)
    }

    fun getAllMedicines(): Flow<List<MedicineEntity>> {
        return medicineDao.getAllMedicines()
    }

    suspend fun deleteMedicine(
        medicine: MedicineEntity
    ) {
        medicineDao.deleteMedicine(medicine)
    }

    suspend fun updateMedicine(
        medicine: MedicineEntity
    ) {
        medicineDao.updateMedicine(medicine)
    }
}