package com.caps.rempasi.presentation.ui.screen.saved

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caps.rempasi.R
import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.presentation.ui.common.UIState
import com.caps.rempasi.presentation.ui.components.ItemSavedRecipe
import com.caps.rempasi.presentation.ui.components.SearchBar

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    viewModel: SavedViewModel = hiltViewModel(),
) {

    val query by viewModel.query
    LaunchedEffect(query) {
        viewModel.searchRecipe(query)
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
            SavedRecipe(
                recipes = recipes,
                query = query,
                onItemClicked = onItemClicked,
                onQueryChanged = viewModel::searchRecipe,
                modifier = modifier
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedRecipe(
    recipes: List<RecipeEntity>,
    query: String,
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    onQueryChanged: (String) -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            SearchBar(query = query, onQueryChange = onQueryChanged)
        }
        if (recipes.isNotEmpty()) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(recipes, key = { it.id }) { item ->
                    ItemSavedRecipe(
                        id = item.id,
                        thumbnail = item.imageUrl,
                        title = item.recipe_name,
                        description = item.ingredients,
                        onItemClicked = onItemClicked,
                    )
                }
            }
        } else {
            if (query == "") {
                NoDataSavedRecipe()
            } else {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Resep tidak ditemukan",
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }

    }
}

@Composable
fun NoDataSavedRecipe(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.no_data),
                contentDescription = "Tidak ada data resep tersimpan"
            )
            Text(text = "Anda belum memiliki resep tersimpan")
        }
    }
}
