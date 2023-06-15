package com.caps.rempasi.presentation.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.caps.rempasi.R
import com.caps.rempasi.data.remote.response.PredictionsItem
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.White

@Composable
fun AnnotatedImageDetectionSection(
    modifier: Modifier = Modifier,
    onCameraClickListener: () -> Unit,
    imageUrl: Uri,
    data: List<PredictionsItem>,
) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .height(200.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .clip(RoundedCornerShape(5.dp))
            .background(White)

    ) {
        SquareBorderedImage(
            imageUri = imageUrl,
            predictions = data,
            modifier = Modifier.align(Alignment.Center),
        )
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 6.dp, bottom = 6.dp)
                .size(30.dp)
                .shadow(
                    elevation = 3.dp,
                    shape = RoundedCornerShape(5.dp)
                )
                .background(White)
                .clip(RoundedCornerShape(5.dp)),
            onClick = onCameraClickListener
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = R.drawable.camera),
                contentDescription = stringResource(R.string.cd_open_camera),
                tint = Red
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_3A)
@Composable
fun PreviewAnnotatedImageDetectionSection() {
    ReMPASITheme {
        AnnotatedImageDetectionSection(
            onCameraClickListener = { },
            imageUrl = "".toUri(),
            data = listOf()
        )
    }
}