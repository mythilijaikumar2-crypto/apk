package com.app.superapp.ui.gov

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun GovServicesDashboard(onServiceClick: (String) -> Unit, onBack: () -> Unit) {
    Scaffold(
        topBar = { AppTopBar(title = "Government Services", onBackClick = onBack) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingMedium)) {
            GovServiceItem("New PAN Card", Icons.Default.Badge) { onServiceClick("pan") }
            GovServiceItem("GST Registration", Icons.Default.Business) { onServiceClick("gst") }
            GovServiceItem("ITR Filing", Icons.Default.Calculate) { onServiceClick("itr") }
            GovServiceItem("Aadhaar Update", Icons.Default.Fingerprint) { onServiceClick("aadhaar") }
            GovServiceItem("Voter ID", Icons.Default.HowToVote) { onServiceClick("voter") }
        }
    }
}

@Composable
fun GovServiceItem(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.fillMaxWidth().padding(vertical = Dimens.SpacingSmall)) {
        ListItem(
            headlineContent = { Text(title) },
            leadingContent = { Icon(icon, null, tint = MaterialTheme.colorScheme.primary) }
        )
    }
}
