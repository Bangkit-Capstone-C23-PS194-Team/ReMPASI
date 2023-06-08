package com.caps.rempasi.presentation.ui.screen.camera_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caps.rempasi.domain.usecase.image_detection.PostImageDetection
import com.caps.rempasi.presentation.ui.common.ProgressState
import com.caps.rempasi.presentation.ui.screen.recomendation.DetectionResult
import com.caps.rempasi.utils.ImageHelper.reduceFileImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import javax.inject.Inject

@HiltViewModel
class CameraResultViewModel @Inject constructor(
    private val postImageDetection: PostImageDetection,
) : ViewModel() {
    private val _state = MutableStateFlow(ProgressState())
    val state = _state.asStateFlow()

    fun resetState() {
        _state.update { ProgressState() }
    }

    private fun setProgress(progress: Float) {
        _state.update {
            it.copy(
                isLoading = true,
                progress = progress
            )
        }
    }

    private fun done(result: DetectionResult) {
        _state.update {
            it.copy(
                isLoading = false,
                progress = 1f,
                done = result
            )
        }
    }

    fun postDataTest(imageResult: ImageResult) {
        val imageUri = imageResult.imageUri
//        setProgress(0.16f)
        val imageFile = imageResult.imageFile
//        setProgress(0.20f)
        val compressedFile = reduceFileImage(imageFile)
//        setProgress(0.32f)
        val requestImageFile = compressedFile.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            compressedFile.name,
            requestImageFile
        )
//        setProgress(0.68f)
        viewModelScope.launch {
            createProgressFlow(0.97f, 1000) // Update progress every second (adjust intervalMillis as needed)
                .onEach { progress -> setProgress(progress) }
                .launchIn(viewModelScope)
            postImageDetection.imageDetection(imageMultipart)
                .collect {
                    done(
                        DetectionResult(
                            imageUri,
                            it
                        )
                    )
                }
        }
    }

    fun createProgressFlow(totalProgress: Float, intervalMillis: Long): Flow<Float> = flow {
        val numSteps = (totalProgress / 0.1f).toInt()
        val progressPerStep = totalProgress / numSteps

        for (i in 1..numSteps) {
            emit(i * progressPerStep)
            delay(intervalMillis)
        }
    }
}