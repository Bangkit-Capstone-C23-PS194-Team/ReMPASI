package com.caps.rempasi.presentation.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.caps.rempasi.presentation.ui.screen.SharedCameraResultViewModel
import com.caps.rempasi.presentation.ui.screen.auth.AuthScreen
import com.caps.rempasi.presentation.ui.screen.auth.WelcomeScreen
import com.caps.rempasi.presentation.ui.screen.camera.CameraResultScreen
import com.caps.rempasi.presentation.ui.screen.home.HomeScreen
import com.caps.rempasi.presentation.ui.screen.onboarding.OnBoardingScreen
import com.caps.rempasi.presentation.ui.screen.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route,
) {

    val sharedViewModel: SharedCameraResultViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen {
                navController.navigate(it)
            }
        }
        composable(route = Screen.Onboarding.route) {
            OnBoardingScreen {
                navController.popBackStack()
                navController.navigate(Screen.Auth.route)
            }
        }
        composable(route = Screen.Home.route) {
            HomeScreen(sharedViewModel) {
                navController.navigate(Screen.CameraResult.route)
            }
        }
        composable(route = Screen.Auth.route) {
            AuthScreen {
                navController.popBackStack()
                navController.navigate(Screen.Welcome.route)
            }
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(name = "Hafid") {
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        }
        composable(
            route = Screen.CameraResult.route,
        ) {
            CameraResultScreen(sharedViewModel) {
                navController.popBackStack()
            }
        }
    }
}