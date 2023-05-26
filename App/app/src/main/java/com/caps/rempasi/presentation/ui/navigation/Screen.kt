package com.caps.rempasi.presentation.ui.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Splash : Screen("splash")
    object Home : Screen("home_page")
    object Profile : Screen("profile_page")
    object DetailRecipe : Screen("detail_recipe_page/{recipeId}") {
        fun createRoute(recipeId: Int) = "detail_recipe_page/$recipeId"
    }
}