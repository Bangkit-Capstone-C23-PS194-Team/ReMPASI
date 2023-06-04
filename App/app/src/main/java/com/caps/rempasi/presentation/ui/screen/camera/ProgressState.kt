package com.caps.rempasi.presentation.ui.screen.camera

data class ProgressState(
    val isLoading: Boolean = false,
    val progress: Float = 0f,
    val done: String? = null
)