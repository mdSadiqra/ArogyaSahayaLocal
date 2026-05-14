package com.sadiq.arogyasahayalocal.data.repository

import com.sadiq.arogyasahayalocal.data.VitalLogEntity
import com.sadiq.arogyasahayalocal.data.local.VitalLogDao
import kotlinx.coroutines.flow.Flow

class VitalLogRepository(
    private val vitalLogDao: VitalLogDao
) {

    suspend fun insertVitalLog(vitalLog: VitalLogEntity) {
        vitalLogDao.insertVitalLog(vitalLog)
    }

    fun getAllVitalLogs(): Flow<List<VitalLogEntity>> {
        return vitalLogDao.getAllVitalLogs()
    }
}