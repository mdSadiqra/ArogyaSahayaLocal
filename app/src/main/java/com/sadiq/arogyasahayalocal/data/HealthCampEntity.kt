package com.sadiq.arogyasahayalocal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_camps")
data class HealthCampEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val title: String,

    val date: String,

    val location: String,

    val workerName: String,

    val campType: String,

    val status: String = "Upcoming",

    val isRegistered: Boolean = false
)