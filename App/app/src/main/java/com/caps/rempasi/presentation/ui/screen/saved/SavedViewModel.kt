package com.caps.rempasi.presentation.ui.screen.saved

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caps.rempasi.data.local.entity.RecipeEntity
import com.caps.rempasi.domain.usecase.recipe.GetListSavedRecipe
import com.caps.rempasi.presentation.ui.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val getListSavedRecipe: GetListSavedRecipe
): ViewModel() {
    private val _uiState: MutableStateFlow<UIState<List<RecipeEntity>>> = MutableStateFlow(UIState.Loading)
    val uiState get() = _uiState.asStateFlow()

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchUsers(query: String) = viewModelScope.launch {
        _query.value = query
        getListSavedRecipe.searchSavedRecipes(_query.value)
            .catch {
                _uiState.value = UIState.Error(it.message.toString())
            }
            .collect { recipes ->
                _uiState.value = UIState.Success(recipes)
            }
    }
}