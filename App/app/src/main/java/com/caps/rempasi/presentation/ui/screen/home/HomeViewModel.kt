package com.caps.rempasi.presentation.ui.screen.home

import android.content.Context
import android.net.Uri
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
        context: Context,
    ){
        viewModelScope.launch {
            cameraRepository.showCameraPreview(
                previewView,
                lifecycleOwner,
                context
            )
        }
    }

    fun captureAndSave(context: Context, successCallback: (Uri) -> Unit){
        viewModelScope.launch {
            cameraRepository.captureAndSaveImage(context, successCallback)
        }
    }

    fun switchFlash(flashMode: Int) {
        viewModelScope.launch {
            cameraRepository.setFlashMode(flashMode)
        }
    }

    fun getFlashMode() = cameraRepository.getFlashMode()
}