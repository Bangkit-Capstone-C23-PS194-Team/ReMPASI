package com.caps.rempasi.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.caps.rempasi.data.local.datastore.AppPreferencesImpl
import com.caps.rempasi.utils.Constants.APP_PREFERENCES
import com.rijaldev.snapgram.data.source.local.datastore.AppPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = APP_PREFERENCES)
@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {

    @Binds
    @Singleton
    abstract fun provideAppPreferences(appPreferencesImpl: AppPreferencesImpl): AppPreferences

    companion object {
        @Provides
        @Singleton
        fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
            context.dataStore
    }
}