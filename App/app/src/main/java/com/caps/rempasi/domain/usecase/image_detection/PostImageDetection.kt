package com.caps.rempasi.domain.usecase.image_detection

import com.caps.rempasi.presentation.ui.screen.recomendation.RecommendationResult
import kotlinx.coroutines.flow.Flow

interface PostImageDetection {
    fun imageDetection(): Flow<RecommendationResult>
}