package com.caps.rempasi.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.caps.rempasi.utils.Constants.ON_BOARDING_STATE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {
    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit {
            it[ON_BOARDING_STATE] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.map {
            it[ON_BOARDING_STATE] ?: false
        }
    }

}