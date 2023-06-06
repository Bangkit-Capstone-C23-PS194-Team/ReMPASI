package com.rijaldev.snapgram.data.source.local.datastore

import kotlinx.coroutines.flow.Flow

interface AppPreferences {
    suspend fun saveOnBoardingState(completed: Boolean)

    fun readOnBoardingState(): Flow<Boolean>
}