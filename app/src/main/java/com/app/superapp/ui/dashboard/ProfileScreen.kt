package com.app.superapp.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.designsystem.Dimens

@Composable
fun ProfileScreen(onLogout: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(Dimens.SpacingMedium)
    ) {
        item {
            ProfileHeader()
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
        }
        
        item {
            KycStatusCard()
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
        }

        item {
            ProfileMenuSection(onLogout)
        }
    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(60.dp))
        }
        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        Text(text = "Agent Name", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(text = "ID: AGT123456", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
fun KycStatusCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.padding(Dimens.SpacingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Verified, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(Dimens.SpacingMedium))
            Column {
                Text(text = "KYC Status: Verified", fontWeight = FontWeight.Bold)
                Text(text = "Full access to all services", style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun ProfileMenuSection(onLogout: () -> Unit) {
    Column {
        ProfileMenuItem(icon = Icons.Default.Edit, title = "Edit Profile")
        ProfileMenuItem(icon = Icons.Default.Description, title = "My Documents")
        ProfileMenuItem(icon = Icons.Default.Settings, title = "Settings")
        ProfileMenuItem(icon = Icons.Default.Help, title = "Help & Support")
        Divider(modifier = Modifier.padding(vertical = Dimens.SpacingSmall))
        ProfileMenuItem(icon = Icons.Default.Logout, title = "Logout", titleColor = MaterialTheme.colorScheme.error, onClick = onLogout)
    }
}

@Composable
fun ProfileMenuItem(
    icon: ImageVector,
    title: String,
    titleColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit = {}
) {
    Surface(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(vertical = Dimens.SpacingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = titleColor)
            Spacer(modifier = Modifier.width(Dimens.SpacingMedium))
            Text(text = title, style = MaterialTheme.typography.bodyLarge, color = titleColor)
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = MaterialTheme.colorScheme.outline)
        }
    }
}
