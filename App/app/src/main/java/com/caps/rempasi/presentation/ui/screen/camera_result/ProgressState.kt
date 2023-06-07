package com.caps.rempasi.presentation.ui.screen.camera_result

import com.caps.rempasi.presentation.ui.screen.recomendation.RecommendationResult

data class ProgressState(
    val isLoading: Boolean = false,
    val progress: Float = 0f,
    val done: RecommendationResult? = null
)