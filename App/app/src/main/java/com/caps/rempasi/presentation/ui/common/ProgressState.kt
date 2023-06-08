package com.caps.rempasi.presentation.ui.common

import com.caps.rempasi.presentation.ui.screen.recomendation.DetectionResult

data class ProgressState(
    val isLoading: Boolean = false,
    val progress: Float = 0f,
    val done: DetectionResult? = null
)