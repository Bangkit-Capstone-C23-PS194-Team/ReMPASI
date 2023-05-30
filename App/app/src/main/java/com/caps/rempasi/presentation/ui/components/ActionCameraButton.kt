package com.caps.rempasi.presentation.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.caps.rempasi.R

@Composable
fun ActionCameraButton(
    icon: Int,
    contentDescription: String,
    onClickAction: () -> Unit,
    ) {
    IconButton(
        onClick = onClickAction,
        modifier = Modifier
            .clip(CircleShape)
            .size(40.dp)
            .background(Color(0xFF0A0118).copy(alpha = 0.4f))
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
    }
}