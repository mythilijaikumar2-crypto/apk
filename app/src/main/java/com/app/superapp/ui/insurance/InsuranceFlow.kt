package com.app.superapp.ui.insurance

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun InsuranceFlow(type: String, onBack: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    
    Scaffold(
        topBar = { AppTopBar(title = "\$type Insurance", onBackClick = onBack) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingLarge)) {
            when (step) {
                0 -> {
                    Text("Enter Details", style = MaterialTheme.typography.titleLarge)
                    AppTextField(value = "", onValueChange = {}, label = if(type == "Motor") "Vehicle Number" else "Member Name")
                    Spacer(modifier = Modifier.weight(1f))
                    AppButton(text = "GET QUOTE", onClick = { step = 1 })
                }
                1 -> {
                    Text("Quotes for you")
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = Dimens.SpacingSmall)) {
                        ListItem(headlineContent = { Text("Standard Plan") }, trailingContent = { Text("₹499/yr") })
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    AppButton(text = "BUY NOW", onClick = { step = 2 })
                }
                2 -> {
                    Text("Policy Issued Successfully!", style = MaterialTheme.typography.headlineMedium)
                    AppButton(text = "DOWNLOAD POLICY", onClick = onBack)
                }
            }
        }
    }
}
