package com.caps.rempasi.presentation.ui.screen.saved

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.caps.rempasi.R
import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.presentation.ui.common.UIState
import com.caps.rempasi.presentation.ui.components.ItemRecipe
import com.caps.rempasi.presentation.ui.components.SearchBar

@Composable
fun SavedScreen(
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    viewModel: SavedViewModel = hiltViewModel(),
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> viewModel.searchUsers(query)
            is UIState.Success -> {
                SavedRecipe(
                    recipes = uiState.data,
                    query = query,
                    onItemClicked = onItemClicked,
                    onQueryChanged = viewModel::searchUsers,
                    modifier = modifier
                )
            }
            is UIState.Error -> {
                Box(modifier = modifier.fillMaxWidth()) {
                    Text(
                        text = uiState.message,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun SavedRecipe(
    recipes: List<RecipeEntity>,
    query: String,
    modifier: Modifier = Modifier,
    onItemClicked: (Int) -> Unit,
    onQueryChanged: (String) -> Unit,
) {
    Column {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            SearchBar(query = query, onQueryChange = onQueryChanged)
        }
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
                        onItemClicked = onItemClicked,
                        isSaved = true
                    )
                }
            } else {
                item {
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
