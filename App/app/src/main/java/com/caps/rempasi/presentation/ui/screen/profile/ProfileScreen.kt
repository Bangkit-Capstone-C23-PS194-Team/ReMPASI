package com.caps.rempasi.presentation.ui.screen.profile

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.caps.rempasi.presentation.ui.components.ButtonLeadingIcon
import com.caps.rempasi.presentation.ui.components.JetTopAppBar
import com.caps.rempasi.presentation.ui.theme.Red
import com.caps.rempasi.presentation.ui.theme.Typography
import com.caps.rempasi.R
import com.caps.rempasi.presentation.ui.theme.ReMPASITheme

@Composable
fun ProfileScreen(
    accountName: String,
    email: String,
    profile: String,
    logOut: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 24.dp)
            .fillMaxSize(),
    ) {
        AsyncImage(
            model = profile,
            contentDescription = "Avatar",
            modifier = Modifier
                .border(2.dp, Red, CircleShape)
                .padding(6.dp)
                .clip(CircleShape)
                .size(140.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = accountName,
            style = Typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = email,
            style = Typography.bodyMedium.copy(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        ButtonLeadingIcon(textTitle = "Keluar", icon = R.drawable.logout) {
            logOut()
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    ReMPASITheme() {
        ProfileScreen(
            accountName = "Akhmadheta Hafid",
            email = "akhmadheta097@gmail.com",
            profile = "https://lh3.googleusercontent.com/ogw/AOLn63F1tQYm1KpakU3JdGmPzMgLR017JuwtOXvv5yL6cA=s32-c-mo",
            logOut = {}
        )
    }
}