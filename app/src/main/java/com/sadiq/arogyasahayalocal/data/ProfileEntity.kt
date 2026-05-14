package com.sadiq.arogyasahayalocal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val fullName: String,

    val age: String,

    val bloodGroup: String,

    val chronicCondition: String,

    val emergencyContact: String,

    val address: String
)