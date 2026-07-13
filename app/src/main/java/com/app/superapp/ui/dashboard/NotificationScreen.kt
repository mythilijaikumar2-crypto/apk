package com.app.superapp.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun NotificationScreen(onBack: () -> Unit) {
    val notifications = listOf(
        NotificationItem("1", "Transaction Successful", "Your AEPS withdrawal of ₹2000 was successful.", "Today", false),
        NotificationItem("2", "New Offer", "Get 5% cashback on your first FASTag recharge.", "Yesterday", true),
        NotificationItem("3", "System Update", "New services added to the dashboard.", "2 days ago", true)
    )

    Scaffold(
        topBar = { AppTopBar(title = "Notifications", onBackClick = onBack) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(notifications) { notification ->
                NotificationRow(notification)
                Divider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        }
    }
}

@Composable
fun NotificationRow(notification: NotificationItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.SpacingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!notification.isRead) {
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(12.dp)
            )
            Spacer(modifier = Modifier.width(Dimens.SpacingMedium))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = notification.title, style = MaterialTheme.typography.titleMedium)
            Text(text = notification.message, style = MaterialTheme.typography.bodyMedium)
            Text(
                text = notification.date,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

data class NotificationItem(
    val id: String,
    val title: String,
    val message: String,
    val date: String,
    val isRead: Boolean
)
