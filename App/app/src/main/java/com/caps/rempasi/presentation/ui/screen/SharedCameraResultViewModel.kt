package com.caps.rempasi.presentation.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.caps.rempasi.presentation.ui.screen.camera_result.ImageResult
import com.caps.rempasi.presentation.ui.screen.recomendation.DetectionResult

class SharedCameraResultViewModel : ViewModel() {
    var imageResult by mutableStateOf<ImageResult?>(null)
        private set

    var detectionResult by mutableStateOf<DetectionResult?>(null)
        private set

    fun postImageResult(newImageResult: ImageResult) {
        imageResult = newImageResult
    }

    fun postRecommendationResult(newDetectionResult: DetectionResult) {
        detectionResult = newDetectionResult
    }
}