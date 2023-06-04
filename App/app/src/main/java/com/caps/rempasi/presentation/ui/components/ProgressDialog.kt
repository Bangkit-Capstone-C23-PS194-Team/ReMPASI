package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.RedSecondary
import com.caps.rempasi.presentation.ui.theme.Typography

@Composable
fun ProgressDialog(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(312.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(RedSecondary)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Deteksi Objek Sedang Berjalan",
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
        )
        Text(
            text = "Menyajikan resep menu MPASI terbaik",
            style = Typography.bodyMedium,
        )

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color.White)
            ) {
                val progressModifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(progress)
                Box(
                    modifier = progressModifier
                        .background(Red)
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${(progress * 100).toInt()}%",
                    style = Typography.bodySmall.copy(
                        fontSize = 8.sp,
                        lineHeight = 10.sp
                    ),
                )

                Text(
                    text = "${(progress * 100).toInt()}/100",
                    style = Typography.bodySmall.copy(
                        fontSize = 8.sp,
                        lineHeight = 10.sp
                    ),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProgressDialogPreview() {
    ReMPASITheme {
        ProgressDialog(0.65f)
    }
}