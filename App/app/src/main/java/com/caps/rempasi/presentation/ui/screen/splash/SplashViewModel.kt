package com.caps.rempasi.presentation.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caps.rempasi.domain.repository.DataStoreRepository
import com.caps.rempasi.presentation.ui.common.UIState
import com.caps.rempasi.presentation.ui.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _startDestination: MutableStateFlow<UIState<String>> =
        MutableStateFlow(UIState.Loading)
    val startDestination get() = _startDestination

    fun getStartDestination() = viewModelScope.launch {
        delay(300)
        repository.readOnBoardingState()
            .catch {
                _startDestination.value = UIState.Error(it.message.toString())
            }
            .collect {
                if (it) _startDestination.value = UIState.Success(Screen.Auth.route)
                else _startDestination.value = UIState.Success(Screen.Onboarding.route)
            }
    }
}