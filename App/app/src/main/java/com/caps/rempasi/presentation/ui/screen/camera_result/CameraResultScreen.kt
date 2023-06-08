package com.caps.rempasi.presentation.ui.screen.camera_result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.components.ActionCameraButton
import com.caps.rempasi.presentation.ui.components.ButtonLeadingIcon
import com.caps.rempasi.presentation.ui.components.CaptureGuideline
import com.caps.rempasi.presentation.ui.components.ProgressDialog
import com.caps.rempasi.presentation.ui.screen.SharedCameraResultViewModel
import com.caps.rempasi.presentation.ui.theme.White

@Composable
fun CameraResultScreen(
    sharedViewModel: SharedCameraResultViewModel,
    modifier: Modifier = Modifier,
    viewModel: CameraResultViewModel = hiltViewModel(),
    navigateToRecommendationResult: () -> Unit,
    navigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val imageResult = sharedViewModel.imageResult

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .clip(RoundedCornerShape(5.dp))
                    .background(White)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageResult?.imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.717f)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    ActionCameraButton(
                        icon = R.drawable.close,
                        contentDescription = "Kembali"
                    ) {
                        navigateBack()
                    }
                }
            }
            CaptureGuideline()
            Spacer(modifier = Modifier.height(16.dp))
            ButtonLeadingIcon(textTitle = "Temukan Resep", icon = R.drawable.find_recipe) {
                viewModel.postDataTest(imageResult?.imageUri!!)
            }
        }

        if (state.isLoading) {
            Dialog(
                onDismissRequest = { },
            ) {
                ProgressDialog(progress = state.progress)
            }
        }
    }

    if (!state.isLoading && state.done != null) {
        state.done?.let {
            LaunchedEffect(key1 = state.done) {
                viewModel.resetState()
                sharedViewModel.postRecommendationResult(it)
                navigateToRecommendationResult()
            }
        }
    }
}