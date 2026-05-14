package com.sadiq.arogyasahayalocal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sadiq.arogyasahayalocal.data.VitalLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalLogDao {

    @Insert
    suspend fun insertVitalLog(vitalLog: VitalLogEntity)

    @Query("SELECT * FROM vital_logs ORDER BY id DESC")
    fun getAllVitalLogs(): Flow<List<VitalLogEntity>>
}