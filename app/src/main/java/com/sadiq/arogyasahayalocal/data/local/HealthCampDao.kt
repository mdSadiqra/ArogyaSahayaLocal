package com.sadiq.arogyasahayalocal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sadiq.arogyasahayalocal.data.HealthCampEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthCampDao {

    /*
    --------------------------------
    INSERT CAMP
    --------------------------------
    */

    @Insert
    suspend fun insertCamp(
        camp: HealthCampEntity
    )

    /*
    --------------------------------
    GET ALL CAMPS
    --------------------------------
    */

    @Query(
        "SELECT * FROM health_camps ORDER BY id DESC"
    )
    fun getAllCamps(): Flow<List<HealthCampEntity>>

    /*
    --------------------------------
    UPDATE CAMP
    --------------------------------
    */

    @Update
    suspend fun updateCamp(
        camp: HealthCampEntity
    )
}