package com.caps.rempasi.utils

import androidx.datastore.preferences.core.booleanPreferencesKey

object Constants {
    const val APP_PREFERENCES = "app_preferences"
    val ON_BOARDING_STATE = booleanPreferencesKey("on_boarding_state")
}