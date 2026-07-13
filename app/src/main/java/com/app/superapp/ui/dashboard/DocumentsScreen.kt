package com.app.superapp.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun DocumentsScreen(onBack: () -> Unit) {
    val docs = listOf("Aadhaar Card", "PAN Card", "Business License", "Shop Photo")

    Scaffold(
        topBar = { AppTopBar(title = "My Documents", onBackClick = onBack) }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            items(docs) { doc ->
                ListItem(
                    headlineContent = { Text(doc) },
                    leadingContent = { Icon(Icons.Default.Description, null) },
                    trailingContent = { IconButton(onClick = {}) { Icon(Icons.Default.Visibility, null) } }
                )
                Divider()
            }
        }
    }
}
