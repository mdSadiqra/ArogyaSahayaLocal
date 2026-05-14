package com.sadiq.arogyasahayalocal.data.repository

import com.sadiq.arogyasahayalocal.data.ProfileEntity
import com.sadiq.arogyasahayalocal.data.local.ProfileDao

class ProfileRepository(
    private val profileDao: ProfileDao
) {

    suspend fun saveProfile(
        profile: ProfileEntity
    ) {
        profileDao.clearOldProfile()
        profileDao.saveProfile(profile)
    }

    suspend fun getProfile(): ProfileEntity? {
        return profileDao.getProfile()
    }
}