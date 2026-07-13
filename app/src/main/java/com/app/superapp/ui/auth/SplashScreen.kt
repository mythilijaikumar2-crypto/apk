package com.app.superapp.ui.auth

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.superapp.core.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigate: (String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000)
        )
        delay(1000) // Extra delay for branding
        viewModel.navigationEvent.collect { navigation ->
            when (navigation) {
                SplashViewModel.SplashNavigation.Onboarding -> onNavigate(Routes.Onboarding.route)
                SplashViewModel.SplashNavigation.Login -> onNavigate(Routes.Login.route)
                SplashViewModel.SplashNavigation.Home -> onNavigate(Routes.Main.route)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "SUPER APP",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.scale(scale.value)
        )
    }
}
