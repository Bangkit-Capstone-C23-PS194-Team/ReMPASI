package com.caps.rempasi.domain.repository

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraRepository {

    suspend fun setFlashMode(flashMode: Int)

    suspend fun captureAndSaveImage(context: Context)

    suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
    )
}