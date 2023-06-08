package com.caps.rempasi.domain.usecase.image_detection

import com.caps.rempasi.data.remote.response.PredictionsItem
import com.caps.rempasi.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class PostImageDetectionInteractor @Inject constructor(
    private val recipeRepository: RecipeRepository
) : PostImageDetection {
    override fun imageDetection(image: MultipartBody.Part): Flow<List<PredictionsItem>> =
        recipeRepository.imageDetection(image)
}