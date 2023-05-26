package com.caps.rempasi.presentation.ui.common

sealed class UIState<out T: Any?> {
    object Loading : UIState<Nothing>()
    data class Success<out T: Any>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}