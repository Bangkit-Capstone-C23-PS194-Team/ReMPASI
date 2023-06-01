package com.caps.rempasi.data.repository

import android.content.ContentValues
import android.content.Context
import android.media.SoundPool
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.caps.rempasi.domain.repository.CameraRepository
import com.caps.rempasi.utils.ImageHelper.FILENAME_FORMAT
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import com.caps.rempasi.R

class CameraRepositoryImpl @Inject constructor(
    private val cameraProvider: ProcessCameraProvider,
    private val selector: CameraSelector,
    private val preview: Preview,
    private val imageAnalysis: ImageAnalysis,
    private val soundPool: SoundPool,
) : CameraRepository {

    private var imageCapture: ImageCapture? = null

    private var flashMode: Int = ImageCapture.FLASH_MODE_ON

    private var soundId = 0

    private var soundPoolLoaded = false

    override suspend fun setFlashMode(flashMode: Int) {
        this.flashMode = flashMode
        imageCapture?.flashMode = flashMode
    }

    override suspend fun captureAndSaveImage(context: Context, successCallback: (Uri) -> Unit) {
//        play sound
        if (soundPoolLoaded) {
            soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
        }

//        file name
        val name = SimpleDateFormat(
            FILENAME_FORMAT, Locale.US
        ).format(System.currentTimeMillis())

//        storing image
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > 28) {
                put(MediaStore.Images.Media.RELATIVE_PATH,"Pictures/ReMPASI")
            }
        }

//        capture output
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        val imageCapture = imageCapture ?: return

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(
                        context,
                        "Gambar berhasil disimpan",
                        Toast.LENGTH_LONG
                    ).show()
                    successCallback(outputFileResults.savedUri!!)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        context,
                        "some error occurred ${exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
    }

    override suspend fun showCameraPreview(
        previewView: PreviewView,
        lifecycleOwner: LifecycleOwner,
        context: Context,
    ) {
        soundPool.setOnLoadCompleteListener { _, _, status ->
            if (status == 0) soundPoolLoaded = true
        }
        soundId = soundPool.load(context, R.raw.camera_capture, 1)

        preview.setSurfaceProvider(previewView.surfaceProvider)
        imageCapture = ImageCapture.Builder()
            .setFlashMode(flashMode)
            .setTargetAspectRatio(AspectRatio.RATIO_4_3)
            .build()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                selector,
                preview,
                imageAnalysis,
                imageCapture
            )
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}