package com.caps.rempasi.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.Typography
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun ButtonEndIcon(
    textTitle: String,
    modifier: Modifier = Modifier,
    pagerState: PagerState? = null,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier,
            visible = if (pagerState == null) true else pagerState.currentPage == 3
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red,
                    contentColor = Color.White
                ),
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = onClick,
            ) {
                Text(
                    text = textTitle,
                    modifier = Modifier
                        .padding(end = 8.dp),
                    style = Typography.headlineMedium.copy(
                        fontSize = 14.sp,
                    )
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Selanjutnya",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun ButtonLeadingIcon(
    textTitle: String,
    icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Red,
            contentColor = Color.White
        ),
        modifier = modifier.padding(horizontal = 15.dp),
        onClick = onClick,
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(24.dp),
        )
        Text(
            text = textTitle,
            style = Typography.headlineMedium.copy(
                fontSize = 14.sp,
            )
        )
    }
}