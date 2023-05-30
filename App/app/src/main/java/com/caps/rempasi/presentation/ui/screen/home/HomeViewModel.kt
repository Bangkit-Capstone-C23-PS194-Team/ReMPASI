package com.caps.rempasi.presentation.ui.screen.home

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.FlashMode
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caps.rempasi.domain.repository.CameraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cameraRepository: CameraRepository
) : ViewModel() {
    fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
    ){
        viewModelScope.launch {
            cameraRepository.showCameraPreview(
                previewView,
                lifecycleOwner,
            )
        }
    }

    fun captureAndSave(context: Context){
        viewModelScope.launch {
            cameraRepository.captureAndSaveImage(context)
        }
    }

    fun switchFlash(flashMode: Int) {
        viewModelScope.launch {
            cameraRepository.setFlashMode(flashMode)
        }
    }

    fun flipCamera() {

    }
}