package com.caps.rempasi.presentation.ui.screen.recomendation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caps.rempasi.presentation.ui.components.AnnotatedImageDetectionSection
import com.caps.rempasi.presentation.ui.components.ItemRecipe
import com.caps.rempasi.presentation.ui.screen.SharedCameraResultViewModel
import com.caps.rempasi.presentation.ui.theme.Typography

@Composable
fun RecommendationScreen(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit,
    sharedViewModel: SharedCameraResultViewModel,
) {
    val imageUrl = sharedViewModel.recommendationResult?.annotatedImage!!
    val recipes = sharedViewModel.recommendationResult?.listRecipe!!
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            text = "Berikut hasil deteksi kami",
            style = Typography.headlineLarge
        )
        AnnotatedImageDetectionSection(onCameraClickListener = { }, imageUrl = imageUrl)
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
                        isSaved = item.isSaved
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