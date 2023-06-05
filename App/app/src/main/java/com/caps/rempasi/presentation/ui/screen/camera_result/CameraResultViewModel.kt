package com.caps.rempasi.presentation.ui.screen.camera_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caps.rempasi.domain.usecase.image_detection.PostImageDetection
import com.caps.rempasi.domain.usecase.recipe.GetRecommendationRecipe
import com.caps.rempasi.presentation.ui.screen.recomendation.RecommendationResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraResultViewModel @Inject constructor(
    private val getRecommendationRecipe: GetRecommendationRecipe,
    private val postImageDetection: PostImageDetection,
) : ViewModel() {
    private val _state = MutableStateFlow(ProgressState())
    val state = _state.asStateFlow()

    fun resetState() {
        _state.update { ProgressState() }
    }

    private fun setProgress(progress: Float) {
        _state.update {
            it.copy(
                isLoading = true,
                progress = progress
            )
        }
    }

    private fun done(result: RecommendationResult) {
        _state.update {
            it.copy(
                isLoading = false,
                progress = 1f,
                done = result
            )
        }
    }

    fun postDataTest() {
        var imageUrl = ""
        setProgress(0.18f)
        viewModelScope.launch {
            delay(2000)
            setProgress(0.3f)
            postImageDetection.imageDetection()
                .collect {
                    imageUrl = it
                }
            delay(2400)
            setProgress(0.93f)
            getRecommendationRecipe.getRecipes()
                .collect {
                    done(RecommendationResult(
                        imageUrl,
                        it
                    ))
                }
        }
    }
}