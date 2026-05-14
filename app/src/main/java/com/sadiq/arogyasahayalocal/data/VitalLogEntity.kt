package com.sadiq.arogyasahayalocal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vital_logs")
data class VitalLogEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val systolicBP: Int,

    val diastolicBP: Int,

    val heartRate: Int,

    val glucoseLevel: Int,

    val date: String
)