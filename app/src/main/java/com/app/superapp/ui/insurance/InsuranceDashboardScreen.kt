package com.app.superapp.ui.insurance

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun InsuranceDashboardScreen(onCategoryClick: (String) -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = { AppTopBar(title = "Insurance", onBackClick = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingMedium)
        ) {
            Text("Protect what matters", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            
            InsuranceCategoryCard("Motor Insurance", Icons.Default.DirectionsCar) { onCategoryClick("Motor") }
            InsuranceCategoryCard("Health Insurance", Icons.Default.HealthAndSafety) { onCategoryClick("Health") }
            InsuranceCategoryCard("Life Insurance", Icons.Default.Favorite) { onCategoryClick("Life") }
            
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            Text("Active Policies", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Box(modifier = Modifier.fillMaxWidth().height(100.dp), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("No active policies found", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
fun InsuranceCategoryCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = Dimens.SpacingSmall)
    ) {
        ListItem(
            headlineContent = { Text(title) },
            leadingContent = { Icon(icon, null, tint = MaterialTheme.colorScheme.primary) },
            trailingContent = { Icon(Icons.Default.ChevronRight, null) }
        )
    }
}
