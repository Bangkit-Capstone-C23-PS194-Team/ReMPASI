package com.caps.rempasi.presentation.ui.screen.recomendation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.presentation.ui.common.UIState
import com.caps.rempasi.presentation.ui.components.AnnotatedImageDetectionSection
import com.caps.rempasi.presentation.ui.components.ItemRecipe
import com.caps.rempasi.presentation.ui.screen.SharedCameraResultViewModel
import com.caps.rempasi.presentation.ui.theme.Typography

@Composable
fun RecommendationScreen(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit,
    viewModel: RecommendationResultViewModel = hiltViewModel(),
    sharedViewModel: SharedCameraResultViewModel,
    onCameraClick: () -> Unit,
) {
    val imageUrl = sharedViewModel.recommendationResult?.annotatedImage!!
    val ingredients = sharedViewModel.recommendationResult?.objectName!!

    LaunchedEffect(ingredients) {
        viewModel.getRecommendationRecipe(ingredients)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetUIState()
        }
    }

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is UIState.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UIState.Success -> {
            val recipes = (uiState as UIState.Success<List<RecipeEntity>>).data
            RecommendationContent(
                modifier = modifier,
                imageUrl = imageUrl,
                recipes = recipes,
                onItemClick = onItemClick,
                onCameraClick = onCameraClick
            )
        }
        is UIState.Error -> {
            val errorMessage = (uiState as UIState.Error).message
            Box(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = errorMessage,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun RecommendationContent(
    imageUrl: String,
    recipes: List<RecipeEntity>,
    viewModel: RecommendationResultViewModel = hiltViewModel(),
    onCameraClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            text = "Berikut hasil deteksi kami",
            style = Typography.headlineLarge
        )
        AnnotatedImageDetectionSection(onCameraClickListener = onCameraClick, imageUrl = imageUrl)
        Text(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp),
            text = "Rekomendasi resep MPASI",
            style = Typography.headlineLarge
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
        ) {
            if (recipes.isNotEmpty()) {
                items(recipes, key = { it.id }) { item ->
                    ItemRecipe(
                        id = item.id,
                        thumbnail = item.imageUrl,
                        title = item.recipe_name,
                        description = item.ingredients,
                        onItemClicked = onItemClick,
                        isSaved = item.isSaved,
                        saveToggle = { viewModel.updateSavedRecipe(item, !item.isSaved) }
                    )
                }
            } else {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "Mohon maaf, kami belum memiliki resep dengan bahan tersebut",
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}