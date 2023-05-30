package com.caps.rempasi.presentation.ui.screen.home

import android.Manifest
import android.os.Build
import androidx.camera.core.ImageCapture.FLASH_MODE_OFF
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.components.ActionCameraButton
import com.caps.rempasi.presentation.ui.components.JetTopAppBar
import com.caps.rempasi.presentation.ui.navigation.Screen
import com.caps.rempasi.utils.UIHelper.showToastPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
//    if (belom login) {
//        LaunchedEffect(key1 = true) {
//            navController.popBackStack()
//            navController.navigate(Screen.Auth.route)
//        }
//    } else {
//
//    }

    val permissions = if (Build.VERSION.SDK_INT <= 28) {
        listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    } else listOf(Manifest.permission.CAMERA)

    val permissionState = rememberMultiplePermissionsState(
        permissions = permissions
    )

    if (!permissionState.allPermissionsGranted) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    var previewView: PreviewView

    var currentFlashMode by remember { mutableStateOf(FLASH_MODE_ON) }

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
                .fillMaxSize()
        ) {
            if (permissionState.allPermissionsGranted) {
                Box(
                    modifier = Modifier
                        .height(screenWidth * 4 / 3)
                        .width(screenWidth)
                ) {
                    AndroidView(
                        factory = {
                            previewView = PreviewView(it)
                            viewModel.showCameraPreview(previewView, lifecycleOwner)
                            previewView
                        },
                        modifier = Modifier
                            .height(screenWidth * 4 / 3)
                            .width(screenWidth)
                    )
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .padding(bottom = 16.dp)
                                .fillMaxWidth(),
                        ) {
                            ActionCameraButton(
                                icon = R.drawable.galery,
                                contentDescription = "Buka Galeri"
                            ) {

                            }
                            IconButton(
                                onClick = {
                                    if (permissionState.allPermissionsGranted) {
                                        viewModel.captureAndSave(context)
                                    } else {
                                        showToastPermission(context)
                                    }
                                },
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(60.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.capture),
                                    modifier = Modifier
                                        .size(60.dp),
                                    contentDescription = "Menangkap Gambar",
                                    tint = Color.White
                                )
                            }
                            ActionCameraButton(
                                icon = if (currentFlashMode == FLASH_MODE_ON) R.drawable.flash_on else R.drawable.flash_off,
                                contentDescription = "Ubah Kamera"
                            ) {
                                currentFlashMode = if (currentFlashMode == FLASH_MODE_ON) {
                                    FLASH_MODE_OFF
                                } else {
                                    FLASH_MODE_ON
                                }
                                viewModel.switchFlash(currentFlashMode)
                            }
                        }
                    }
                }
            }
        }
    }
}