package com.caps.rempasi.presentation.ui.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Splash : Screen("splash")
    object Home : Screen("home_page")
    object CameraResult : Screen("camera_result")
    object Auth : Screen("auth_screen")
    object Welcome : Screen("welcome_screen")
    object Profile : Screen("profile_page")
    object Saved : Screen("bookmark_page")
    object RecommendationResult : Screen("recommendation_result")
    object DetailRecipe : Screen("detail_recipe_page/{recipeId}") {
        fun createRoute(recipeId: Int) = "detail_recipe_page/$recipeId"
    }
}