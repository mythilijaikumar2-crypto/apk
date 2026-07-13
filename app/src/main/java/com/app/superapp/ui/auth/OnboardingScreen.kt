package com.app.superapp.ui.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.core.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onNavigate: (String) -> Unit) {
    val pages = listOf(
        OnboardingPage("Welcome", "Manage all your financial services in one place."),
        OnboardingPage("Banking", "AEPS, DMT, Mini ATM and more."),
        OnboardingPage("Payments", "Bill payments, recharges, and insurance.")
    )
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingContent(pages[page])
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SpacingMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { onNavigate(Routes.LanguageSelect.route) }) {
                Text("SKIP")
            }
            
            AppButton(
                text = if (pagerState.currentPage == pages.size - 1) "GET STARTED" else "NEXT",
                onClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    } else {
                        onNavigate(Routes.LanguageSelect.route)
                    }
                },
                modifier = Modifier.width(150.dp)
            )
        }
    }
}

data class OnboardingPage(val title: String, val description: String)

@Composable
fun OnboardingContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.SpacingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Placeholder for illustration
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(Dimens.SpacingLarge),
            contentAlignment = Alignment.Center
        ) {
            Text("Illustration", style = MaterialTheme.typography.headlineMedium)
        }
        
        Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
        
        Text(
            text = page.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        
        Text(
            text = page.description,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
