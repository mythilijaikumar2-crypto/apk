package com.app.superapp.ui.support

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun SupportDashboard(onBack: () -> Unit) {
    Scaffold(
        topBar = { AppTopBar(title = "Help & Support", onBackClick = onBack) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingMedium)) {
            SupportItem("FAQs", Icons.Default.QuestionAnswer)
            SupportItem("Contact Us", Icons.Default.ContactSupport)
            SupportItem("Raise a Ticket", Icons.Default.ConfirmationNumber)
            SupportItem("Feedback", Icons.Default.RateReview)
        }
    }
}

@Composable
fun SupportItem(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Card(modifier = Modifier.fillMaxWidth().padding(vertical = Dimens.SpacingSmall)) {
        ListItem(
            headlineContent = { Text(title) },
            leadingContent = { Icon(icon, null, tint = MaterialTheme.colorScheme.primary) }
        )
    }
}
