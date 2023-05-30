package com.caps.rempasi.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caps.rempasi.presentation.ui.theme.Black
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.RedSecondary
import com.caps.rempasi.presentation.ui.theme.Typography
import com.caps.rempasi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JetTopAppBar(
    modifier: Modifier = Modifier,
    onAboutPageClicked: () -> Unit = {},
    showBackButton: Boolean = false,
    pageTitle: String = "",
    onBackClicked: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = RedSecondary
        ),
        navigationIcon = {
            if (showBackButton) {
                IconButton(
                    modifier = Modifier,
                    onClick = { onBackClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.arrow_back)
                    )
                }
            } else {
                Icon(
                    painter = painterResource(R.drawable.logo_temporary),
                    contentDescription = stringResource(R.string.logo_content_desc),
                    tint = Red,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(width = 125.dp, height = 40.dp)
                )
            }
        },
        title = {
            if (showBackButton) {
                Text(
                    text = pageTitle,
                    style = Typography.titleLarge.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        },
        actions = {
            if (!showBackButton) {
                IconButton(
                    onClick = { onAboutPageClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = stringResource(R.string.profile_page),
                        tint = Black
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_3A)
@Composable
fun AppBarPreview() {
    JetTopAppBar(
        onAboutPageClicked = {},
        showBackButton = false,
    )
}