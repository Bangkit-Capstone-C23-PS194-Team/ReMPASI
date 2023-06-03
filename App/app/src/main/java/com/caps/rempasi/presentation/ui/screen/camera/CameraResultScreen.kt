package com.caps.rempasi.presentation.ui.screen.camera

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.components.ButtonLeadingIcon
import com.caps.rempasi.presentation.ui.components.CaptureGuideline
import com.caps.rempasi.presentation.ui.components.JetTopAppBar
import com.caps.rempasi.presentation.ui.screen.SharedCameraResultViewModel
import com.caps.rempasi.presentation.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraResultScreen(
    sharedViewModel: SharedCameraResultViewModel,
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit,
    navigateBack: () -> Unit,
) {
    val imageResult = sharedViewModel.imageResult

    Scaffold(
        topBar = {
            JetTopAppBar(
                onAboutPageClicked = navigateToProfile,
                showBackButton = false,
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageResult?.imageUri),
                    contentDescription = null
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = navigateBack,
                        modifier = Modifier
                            .size(40.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "Kembali",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                }
            }
            CaptureGuideline()
            Spacer(modifier = Modifier.height(16.dp))
            ButtonLeadingIcon(textTitle = "Temukan Resep", icon = R.drawable.find_recipe) {

            }
        }
    }
}