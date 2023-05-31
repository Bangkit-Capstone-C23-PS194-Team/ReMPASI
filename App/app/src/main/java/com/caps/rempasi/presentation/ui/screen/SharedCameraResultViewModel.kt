package com.caps.rempasi.presentation.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.caps.rempasi.presentation.ui.screen.camera.ImageResult

class SharedCameraResultViewModel : ViewModel() {
    var imageResult by mutableStateOf<ImageResult?>(null)
        private set

    fun postImageResult(newImageResult: ImageResult) {
        imageResult = newImageResult
    }
}