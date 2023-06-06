package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.Typography

@Composable
fun TitleSection(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = Typography.headlineMedium,
        color = Red,
        modifier = modifier
            .padding(bottom = 8.dp)
    )
}