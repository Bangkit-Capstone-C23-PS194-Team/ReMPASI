package com.caps.rempasi.presentation.ui

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.caps.rempasi.presentation.ui.components.JetTopAppBar
import com.caps.rempasi.presentation.ui.navigation.NavigationGraph
import com.caps.rempasi.presentation.ui.navigation.Screen
import com.caps.rempasi.presentation.ui.screen.auth.GoogleAuthUiClient
import com.caps.rempasi.presentation.ui.navigation.BottomBar
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun RempasiApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
    appContext: Context,
    lifeCycleScope: LifecycleCoroutineScope
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when (currentRoute) {
                Screen.Saved.route, Screen.Home.route, Screen.Profile.route, Screen.CameraResult.route, Screen.RecommendationResult.route  -> {
                    JetTopAppBar()
                }
                Screen.DetailRecipe.route -> {
                    JetTopAppBar(
                        showBackButton = true,
                        pageTitle = "Detail Resep",
                        onBackClicked = {
                            navController.popBackStack()
                        }
                    )
                }
            }

        },
        bottomBar = {
            when (currentRoute) {
                Screen.Saved.route, Screen.Home.route, Screen.CameraResult.route, Screen.RecommendationResult.route, Screen.Profile.route -> {
                    BottomBar(navController)
                }
            }
        }
    ) {innerPadding ->
        NavigationGraph(
            navController = navController,
            googleAuthUiClient = googleAuthUiClient,
            appContext = appContext,
            lifeCycleScope = lifeCycleScope,
            innerPadding = innerPadding
        )
    }
}