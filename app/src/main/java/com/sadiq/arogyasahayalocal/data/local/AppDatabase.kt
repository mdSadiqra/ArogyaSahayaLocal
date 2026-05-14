package com.sadiq.arogyasahayalocal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sadiq.arogyasahayalocal.data.HealthCampEntity
import com.sadiq.arogyasahayalocal.data.MedicineEntity
import com.sadiq.arogyasahayalocal.data.ProfileEntity
import com.sadiq.arogyasahayalocal.data.UserEntity
import com.sadiq.arogyasahayalocal.data.VitalLogEntity

@Database(
    entities = [
        MedicineEntity::class,
        VitalLogEntity::class,
        ProfileEntity::class,
        UserEntity::class,
        HealthCampEntity::class
    ],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    /*
    --------------------------------
    MEDICINE
    --------------------------------
    */

    abstract fun medicineDao(): MedicineDao

    /*
    --------------------------------
    VITAL LOG
    --------------------------------
    */

    abstract fun vitalLogDao(): VitalLogDao

    /*
    --------------------------------
    USER LOGIN / REGISTER
    --------------------------------
    */

    abstract fun userDao(): UserDao

    /*
    --------------------------------
    MEDICAL PROFILE
    --------------------------------
    */

    abstract fun profileDao(): ProfileDao

    /*
    --------------------------------
    HEALTH CAMP
    --------------------------------
    */

    abstract fun healthCampDao(): HealthCampDao
}