package com.caps.rempasi.domain.usecase.image_detection

import com.caps.rempasi.domain.repository.RecipeRepository
import com.caps.rempasi.presentation.ui.screen.recomendation.RecommendationResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostImageDetectionInteractor @Inject constructor(
    private val recipeRepository: RecipeRepository
) : PostImageDetection {
    override fun imageDetection(): Flow<RecommendationResult> = recipeRepository.imageDetection()
}