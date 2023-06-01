package com.caps.rempasi.di

import com.caps.rempasi.data.repository.CameraRepositoryImpl
import com.caps.rempasi.data.repository.DataStoreRepositoryImpl
import com.caps.rempasi.domain.repository.CameraRepository
import com.caps.rempasi.domain.repository.DataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository

    @Binds
    @Singleton
    abstract fun provideCameraRepository(cameraRepositoryImpl: CameraRepositoryImpl): CameraRepository
}