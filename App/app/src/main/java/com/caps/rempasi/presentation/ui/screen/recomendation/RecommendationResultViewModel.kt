package com.caps.rempasi.presentation.ui.screen.recomendation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.domain.usecase.recipe.GetRecommendationRecipe
import com.caps.rempasi.domain.usecase.recipe.UpdateSavedRecipe
import com.caps.rempasi.presentation.ui.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendationResultViewModel @Inject constructor(
    private val getRecommendationRecipe: GetRecommendationRecipe,
    private val updateSavedRecipe: UpdateSavedRecipe
): ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<RecipeEntity>>> = MutableStateFlow(UIState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun resetUIState() {
        _uiState.value = UIState.Loading
    }

    fun getRecommendationRecipe(query: List<String>) = viewModelScope.launch {
        _uiState.value = UIState.Loading
        getRecommendationRecipe.getRecipes(query)
            .catch {
                _uiState.value = UIState.Error(it.message.toString())
            }
            .collect { recipes ->
                _uiState.value = UIState.Success(recipes)
            }
    }

    fun updateSavedRecipe(recipe: RecipeEntity, isSaved: Boolean) {
        viewModelScope.launch {
            updateSavedRecipe.updateRecipe(recipe, isSaved)
        }
    }
}