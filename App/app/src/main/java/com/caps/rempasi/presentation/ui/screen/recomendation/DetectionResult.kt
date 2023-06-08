package com.caps.rempasi.presentation.ui.screen.recomendation

import android.net.Uri
import android.os.Parcelable
import com.caps.rempasi.data.remote.response.PredictionsItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectionResult(
    val imageUri: Uri,
    val predictions: List<PredictionsItem>
) : Parcelable