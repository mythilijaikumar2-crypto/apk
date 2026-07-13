package com.app.superapp.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun KycUploadScreen(onNavigate: () -> Unit) {
    Scaffold(
        topBar = { AppTopBar(title = "KYC Documents") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge)
        ) {
            Text(
                text = "Upload Documents",
                style = MaterialTheme.typography.headlineSmall
            )
            
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)
            ) {
                item { DocumentPickerItem(label = "Aadhaar Front") }
                item { DocumentPickerItem(label = "Aadhaar Back") }
                item { DocumentPickerItem(label = "PAN Card") }
                item { DocumentPickerItem(label = "Shop Photo") }
            }
            
            AppButton(text = "SUBMIT KYC", onClick = onNavigate)
        }
    }
}

@Composable
fun DocumentPickerItem(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.medium)
            .clickable { /* Mock Picker */ },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Add, contentDescription = null)
            Text(text = label, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
