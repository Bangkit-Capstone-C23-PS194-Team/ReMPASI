package com.caps.rempasi.presentation.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.caps.rempasi.presentation.ui.screen.auth.AuthScreen
import com.caps.rempasi.presentation.ui.screen.auth.WelcomeScreen
import com.caps.rempasi.presentation.ui.screen.camera.CameraResultScreen
import com.caps.rempasi.presentation.ui.screen.camera.ImageResult
import com.caps.rempasi.presentation.ui.screen.home.HomeScreen
import com.caps.rempasi.presentation.ui.screen.onboarding.OnBoardingScreen
import com.caps.rempasi.presentation.ui.screen.splash.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navHostController = navController)
        }
        composable(route = Screen.Onboarding.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Auth.route) {
            AuthScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController, name = "Hafid")
        }
        composable(
            route = Screen.CameraResult.route,
            arguments = listOf(
                navArgument("imageResult") {
                    type = NavType.ParcelableType(ImageResult::class.java)
                }
            ),
        ) {
            val imageResult = it.arguments?.getParcelable<ImageResult>("imageResult")
            imageResult?.let {
                CameraResultScreen(navController = navController, imageResult)
            }
        }
    }
}