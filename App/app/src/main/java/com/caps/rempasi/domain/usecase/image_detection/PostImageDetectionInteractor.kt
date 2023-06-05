package com.caps.rempasi.domain.usecase.image_detection

import com.caps.rempasi.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostImageDetectionInteractor @Inject constructor(
    private val recipeRepository: RecipeRepository
) : PostImageDetection {
    override fun imageDetection(): Flow<String> = recipeRepository.imageDetection()
}