package com.caps.rempasi.presentation.ui.screen.auth

data class SignInState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
