package com.caps.rempasi.presentation.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.caps.rempasi.presentation.ui.screen.camera.ImageResult
import com.caps.rempasi.presentation.ui.screen.recomendation.RecommendationResult

class SharedCameraResultViewModel : ViewModel() {
    var imageResult by mutableStateOf<ImageResult?>(null)
        private set

    var recommendationResult by mutableStateOf<RecommendationResult?>(null)
        private set

    fun postImageResult(newImageResult: ImageResult) {
        imageResult = newImageResult
    }

    fun postRecommendationResult(newRecommendationResult: RecommendationResult) {
        recommendationResult = newRecommendationResult
    }
}