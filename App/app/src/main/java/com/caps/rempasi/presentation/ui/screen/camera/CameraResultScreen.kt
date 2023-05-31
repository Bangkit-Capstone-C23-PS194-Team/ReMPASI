package com.caps.rempasi.presentation.ui.screen.camera

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.components.ActionCameraButton
import com.caps.rempasi.presentation.ui.components.JetTopAppBar
import com.caps.rempasi.presentation.ui.screen.SharedCameraResultViewModel
import com.caps.rempasi.utils.ImageHelper.toFile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraResultScreen(
    viewModel: SharedCameraResultViewModel,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val imageResult = viewModel.imageResult

    LaunchedEffect(key1 = imageResult) {
        if (imageResult != null) {
            Log.d("ada", "${imageResult.imageUri}")
        } else {
            Log.d("ada", "tidak ada ${imageResult?.imageUri}")
        }
    }
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
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(screenWidth * 4 / 3)
                    .width(screenWidth)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageResult?.imageUri),
                    contentDescription = null
                )
                ActionCameraButton(
                    icon = R.drawable.close,
                    contentDescription = "Kembali"
                ) {
                    navigateBack()
                }
            }
        }
    }
}