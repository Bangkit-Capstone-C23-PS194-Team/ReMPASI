package com.caps.rempasi.di

import androidx.camera.core.*
import com.caps.rempasi.domain.usecase.GetListSavedRecipeUseCase
import com.caps.rempasi.domain.usecase.GetListSavedRecipeUseCaseInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideGetListSavedRecipeUseCase(getListSavedRecipeUseCaseInteractor: GetListSavedRecipeUseCaseInteractor): GetListSavedRecipeUseCase
}