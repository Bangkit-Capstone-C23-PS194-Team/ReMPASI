package com.caps.rempasi.domain.repository

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import com.caps.rempasi.presentation.ui.screen.camera.ImageResult

interface CameraRepository {

    suspend fun setFlashMode(flashMode: Int)

    suspend fun captureAndSaveImage(context: Context, successCallback: (Uri) -> Unit)

    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        context: Context,
    )
}