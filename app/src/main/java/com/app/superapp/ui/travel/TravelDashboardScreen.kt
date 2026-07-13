package com.app.superapp.ui.travel

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun TravelDashboardScreen(onCategoryClick: (String) -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = { AppTopBar(title = "Travel", onBackClick = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingMedium)
        ) {
            Text("Where are you going?", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
                TravelCategoryCard("Bus", Icons.Default.DirectionsBus, Modifier.weight(1f)) { onCategoryClick("Bus") }
                TravelCategoryCard("Train", Icons.Default.Train, Modifier.weight(1f)) { onCategoryClick("Train") }
            }
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
                TravelCategoryCard("Flight", Icons.Default.Flight, Modifier.weight(1f)) { onCategoryClick("Flight") }
                TravelCategoryCard("Hotel", Icons.Default.Hotel, Modifier.weight(1f)) { onCategoryClick("Hotel") }
            }
            
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            Text("Upcoming Trips", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            // Placeholder for bookings
            Box(modifier = Modifier.fillMaxWidth().height(100.dp), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("No upcoming trips", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun TravelCategoryCard(title: String, icon: ImageVector, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, null, modifier = Modifier.size(32.dp))
            Text(title, fontWeight = FontWeight.Medium)
        }
    }
}
