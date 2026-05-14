package com.sadiq.arogyasahayalocal.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sadiq.arogyasahayalocal.data.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    /*
    --------------------------------
    INSERT MEDICINE
    --------------------------------
    */

    @Insert
    suspend fun insertMedicine(
        medicine: MedicineEntity
    )

    /*
    --------------------------------
    GET ALL MEDICINES
    --------------------------------
    */

    @Query(
        "SELECT * FROM medicines ORDER BY id DESC"
    )
    fun getAllMedicines(): Flow<List<MedicineEntity>>

    /*
    --------------------------------
    DELETE MEDICINE
    --------------------------------
    */

    @Delete
    suspend fun deleteMedicine(
        medicine: MedicineEntity
    )

    /*
    --------------------------------
    UPDATE MEDICINE
    --------------------------------
    */

    @Update
    suspend fun updateMedicine(
        medicine: MedicineEntity
    )
}