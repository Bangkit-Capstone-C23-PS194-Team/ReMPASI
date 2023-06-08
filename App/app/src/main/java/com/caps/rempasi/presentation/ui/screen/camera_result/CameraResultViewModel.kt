package com.caps.rempasi.presentation.ui.screen.camera_result

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caps.rempasi.domain.usecase.image_detection.PostImageDetection
import com.caps.rempasi.presentation.ui.common.ProgressState
import com.caps.rempasi.presentation.ui.screen.recomendation.DetectionResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    fun postDataTest(imageUri: Uri) {
        setProgress(0.18f)
        viewModelScope.launch {
            setProgress(0.34f)
            delay(700)
            setProgress(0.51f)
            delay(8000)
            postImageDetection.imageDetection()
                .collect {
                    setProgress(0.98f)
                    delay(800)
                    done(
                        DetectionResult(
                            imageUri,
                            it
                        )
                    )
                }
        }
    }
}