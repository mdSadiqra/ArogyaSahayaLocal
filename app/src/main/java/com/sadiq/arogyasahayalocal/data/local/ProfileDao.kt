package com.sadiq.arogyasahayalocal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadiq.arogyasahayalocal.data.ProfileEntity

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProfile(
        profile: ProfileEntity
    )

    @Query("SELECT * FROM profile LIMIT 1")
    suspend fun getProfile(): ProfileEntity?

    @Query("DELETE FROM profile")
    suspend fun clearOldProfile()
}