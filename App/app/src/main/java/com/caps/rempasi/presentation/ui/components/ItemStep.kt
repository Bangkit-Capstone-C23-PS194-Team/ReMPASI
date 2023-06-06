package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme
import com.caps.rempasi.presentation.ui.theme.RedAccent
import com.caps.rempasi.presentation.ui.theme.Typography

@Composable
fun ItemStep(
    number: Int,
    step: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            color = RedAccent,
            modifier = Modifier
                .size(25.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = number.toString(),
                textAlign = TextAlign.Center,
                color = Color.Black,
                style = Typography.bodyMedium.copy(
                    lineHeight = 18.sp
                )
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = step,
            style = Typography.bodyMedium.copy(
                lineHeight = 18.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemStepPreview() {
    ReMPASITheme {
        ItemStep(
            number = 12,
            step = "Siapkan semua bahan, cincang halus hati ayam, wortel dan brokoli"
        )
    }
}