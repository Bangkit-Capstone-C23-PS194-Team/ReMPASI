package com.caps.rempasi.di

import androidx.camera.core.*
import com.caps.rempasi.domain.usecase.image_detection.PostImageDetection
import com.caps.rempasi.domain.usecase.image_detection.PostImageDetectionInteractor
import com.caps.rempasi.domain.usecase.recipe.*
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
    abstract fun provideGetListSavedRecipe(getListSavedRecipeInteractor: GetListSavedRecipeInteractor): GetListSavedRecipe

    @Binds
    @Singleton
    abstract fun providePostImageDetection(postImageDetectionInteractor: PostImageDetectionInteractor): PostImageDetection

    @Binds
    @Singleton
    abstract fun provideGetRecommendationRecipe(getRecommendationRecipeInteractor: GetRecommendationRecipeInteractor): GetRecommendationRecipe

    @Binds
    @Singleton
    abstract fun provideGetDetailRecipe(detailRecipeInteractor: DetailRecipeInteractor): DetailRecipe
}