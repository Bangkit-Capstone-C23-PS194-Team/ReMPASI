package com.caps.rempasi.presentation.ui.screen.home

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture.FLASH_MODE_OFF
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.components.ActionCameraButton
import com.caps.rempasi.presentation.ui.components.CaptureGuideline
import com.caps.rempasi.presentation.ui.screen.SharedCameraResultViewModel
import com.caps.rempasi.presentation.ui.screen.camera_result.ImageResult
import com.caps.rempasi.utils.ImageHelper.toFile
import com.caps.rempasi.utils.UIHelper.showToastPermission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    sharedViewModel: SharedCameraResultViewModel,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToResult: () -> Unit,
) {
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
    var previewView: PreviewView

    val launcherGallery =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            uri?.let {
                val file = it.toFile(context)
                file.let { f ->
                    sharedViewModel.postImageResult(
                        ImageResult(
                            f, it
                        )
                    )
                    navigateToResult()
                }
            }
        }

    var currentFlashMode by remember { mutableStateOf(viewModel.getFlashMode()) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (permissionState.allPermissionsGranted) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                AndroidView(
                    factory = {
                        previewView = PreviewView(it)
                        viewModel.showCameraPreview(previewView, lifecycleOwner, context)
                        previewView
                    },
                    modifier = Modifier
                        .fillMaxSize()
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
                            contentDescription = stringResource(R.string.cd_open_gallery)
                        ) {
                            val mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                            launcherGallery.launch(PickVisualMediaRequest(mediaType))
                        }
                        IconButton(
                            onClick = {
                                if (permissionState.allPermissionsGranted) {
                                    viewModel.captureAndSave(context) {
                                        sharedViewModel.postImageResult(
                                            ImageResult(
                                                it.toFile(
                                                    context
                                                ), it
                                            )
                                        )
                                        navigateToResult()
                                    }
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
                                contentDescription = stringResource(R.string.cd_capture_image),
                                tint = Color.White
                            )
                        }
                        ActionCameraButton(
                            icon = if (currentFlashMode == FLASH_MODE_ON) R.drawable.flash_on else R.drawable.flash_off,
                            contentDescription = stringResource(R.string.cd_flash_mode)
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
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                CaptureGuideline()
            }
        }
    }
}