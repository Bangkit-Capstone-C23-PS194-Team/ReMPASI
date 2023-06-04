package com.caps.rempasi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.caps.rempasi.presentation.ui.RempasiApp
import com.caps.rempasi.presentation.ui.screen.auth.GoogleAuthUiClient
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContext = applicationContext
        val lifeCycleScope = lifecycleScope

        setContent {
            ReMPASITheme {
                val navController = rememberNavController()
                RempasiApp(
                    navController = navController,
                    googleAuthUiClient = googleAuthUiClient,
                    appContext = appContext,
                    lifeCycleScope = lifeCycleScope
                )
            }
        }
    }
}