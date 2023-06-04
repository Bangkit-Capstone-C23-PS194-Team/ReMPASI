package com.caps.rempasi.presentation.ui.screen.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CameraResultViewModel : ViewModel() {
    private val _state = MutableStateFlow(ProgressState())
    val state = _state.asStateFlow()

    fun resetState() {
        _state.update { ProgressState() }
    }

    fun setProgress(progress: Float) {
        _state.update {
            it.copy(
                isLoading = true,
                progress = progress
            )
        }
    }

    fun done(message: String) {
        _state.update {
            it.copy(
                isLoading = false,
                progress = 1f,
                done = message
            )
        }
    }

    fun postDataTest() {
        viewModelScope.launch {
            setProgress(0.2f)
            delay(3000)
            setProgress(0.6f)
            delay(600)
            setProgress(0.8f)
            delay(4000)
            done("done")
        }
    }
}