package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caps.rempasi.presentation.ui.theme.Typography

@Composable
fun CaptureGuideline(
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Pastikan semua bahan makanan tidak saling menumpuk dan terlihat dalam kamera ya Moms",
        style = Typography.bodyMedium.copy(
            lineHeight = 24.sp
        ),
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    )
}