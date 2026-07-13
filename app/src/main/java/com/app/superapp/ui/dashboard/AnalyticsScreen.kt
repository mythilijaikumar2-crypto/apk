package com.app.superapp.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun AnalyticsScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = { AppTopBar(title = "Analytics", onBackClick = onBack) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingMedium)) {
            Text("Business Performance", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            
            // Mock Chart Placeholder
            Card(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    Text("Commission Earnings Chart (Vico)")
                }
            }
            
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
                AnalyticsStatCard("Transactions", "145", Modifier.weight(1f))
                AnalyticsStatCard("Success Rate", "98%", Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun AnalyticsStatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(Dimens.SpacingMedium)) {
            Text(title, style = MaterialTheme.typography.labelMedium)
            Text(value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        }
    }
}
