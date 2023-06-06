package com.caps.rempasi.presentation.ui.screen.camera

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class ImageResult(
    val imageFile: File,
    val imageUri: Uri,
) : Parcelable