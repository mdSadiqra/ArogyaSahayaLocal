package com.sadiq.arogyasahayalocal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class MedicineEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val medicineName: String,

    val dosage: String,

    val morning: Boolean = false,

    val afternoon: Boolean = false,

    val night: Boolean = false,

    /*
    --------------------------------
    REMINDER TIME
    --------------------------------
    */

    val reminderHour: String = "08",

    val reminderMinute: String = "00",

    val reminderPeriod: String = "AM"
)