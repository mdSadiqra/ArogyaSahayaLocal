package com.sadiq.arogyasahayalocal.data.repository

import com.sadiq.arogyasahayalocal.data.HealthCampEntity
import com.sadiq.arogyasahayalocal.data.local.HealthCampDao
import kotlinx.coroutines.flow.Flow

class HealthCampRepository(
    private val healthCampDao: HealthCampDao
) {

    /*
    --------------------------------
    INSERT CAMP
    --------------------------------
    */

    suspend fun insertCamp(
        camp: HealthCampEntity
    ) {
        healthCampDao.insertCamp(camp)
    }

    /*
    --------------------------------
    GET ALL CAMPS
    --------------------------------
    */

    fun getAllCamps(): Flow<List<HealthCampEntity>> {
        return healthCampDao.getAllCamps()
    }

    /*
    --------------------------------
    UPDATE CAMP
    --------------------------------
    */

    suspend fun updateCamp(
        camp: HealthCampEntity
    ) {
        healthCampDao.updateCamp(camp)
    }
}