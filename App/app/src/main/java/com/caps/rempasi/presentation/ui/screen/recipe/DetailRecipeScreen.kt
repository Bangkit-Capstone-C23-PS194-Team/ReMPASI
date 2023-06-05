package com.caps.rempasi.presentation.ui.screen.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.caps.rempasi.presentation.ui.common.UIState
import com.caps.rempasi.presentation.ui.components.DetailContent

@Composable
fun DetailRecipeScreen(
    id: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailRecipeViewModel = hiltViewModel()
) {
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> viewModel.getDetailRecipe(id)
            is UIState.Success -> {
                val recipe = uiState.data
                DetailContent(
                    id = recipe.id,
                    name = recipe.recipe_name,
                    thumbnail = recipe.imageUrl,
                    ingredients = recipe.ingredients,
                    steps = recipe.steps,
                    isSaved = recipe.isSaved,
                    saveToggle = {},
                    modifier = modifier
                )
            }
            is UIState.Error -> {
                Box(modifier = modifier.fillMaxWidth()) {
                    Text(
                        text = uiState.message, modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}