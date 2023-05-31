package com.caps.rempasi.presentation.ui.navigation

import com.caps.rempasi.presentation.ui.screen.camera.ImageResult

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Splash : Screen("splash")
    object Home : Screen("home_page")
    object CameraResult : Screen("camera_result")
    object Auth : Screen("auth_screen")
    object Welcome : Screen("welcome_screen")

    object Profile : Screen("profile_page")
    object DetailRecipe : Screen("detail_recipe_page/{recipeId}") {
        fun createRoute(recipeId: Int) = "detail_recipe_page/$recipeId"
    }
}