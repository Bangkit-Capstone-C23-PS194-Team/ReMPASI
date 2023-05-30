package com.caps.rempasi.presentation.ui.screen.camera

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.caps.rempasi.presentation.ui.components.JetTopAppBar
import com.caps.rempasi.utils.ImageHelper.IMAGE_RESULT_KEY

@Composable
fun CameraResultScreen(
    navController: NavHostController,
    imageResult: ImageResult,
) {
    CameraResultContent(imageResult = imageResult)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraResultContent(
    imageResult: ImageResult,
    modifier: Modifier = Modifier,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Scaffold(
        topBar = {
            JetTopAppBar(
                onAboutPageClicked = {},
                showBackButton = false,
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(screenWidth * 4 / 3)
                    .width(screenWidth)
            ) {
                AsyncImage(
                    model = if (imageResult.isFromCamera) imageResult.imageBitmap else imageResult.imageUri,
                    contentDescription = null
                )
            }
        }
    }
}