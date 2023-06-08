package com.caps.rempasi.presentation.ui.components

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun DisableBackPressed() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val onBackPressedDispatcherOwner = LocalOnBackPressedDispatcherOwner.current

    DisposableEffect(onBackPressedDispatcherOwner) {
        val onBackPressedCallback = object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                // Disable the back button press
            }
        }

        onBackPressedDispatcherOwner?.onBackPressedDispatcher?.addCallback(
            lifecycleOwner,
            onBackPressedCallback
        )

        val lifecycleObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                onBackPressedCallback.remove()
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            onBackPressedCallback.remove()
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
}
