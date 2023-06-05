package com.caps.rempasi.presentation.ui.screen.recipe

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.domain.usecase.recipe.DetailRecipe
import com.caps.rempasi.presentation.ui.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailRecipeViewModel @Inject constructor(
    private val detailRecipe: DetailRecipe
) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState<RecipeEntity>> = MutableStateFlow(UIState.Loading)
    val uiState get() = _uiState.asStateFlow()

    fun getDetailRecipe(id: Int) = viewModelScope.launch {
        detailRecipe.getRecipe(id)
            .catch {
                _uiState.value = UIState.Error(it.message.toString())
            }
            .collect {
                _uiState.value = UIState.Success(it)
            }
    }
}