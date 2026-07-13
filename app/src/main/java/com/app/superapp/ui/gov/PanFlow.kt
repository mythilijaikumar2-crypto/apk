package com.app.superapp.ui.gov

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppStepper
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun PanFlow(onBack: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    
    Scaffold(topBar = { AppTopBar(title = "New PAN Application", onBackClick = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingLarge)) {
            AppStepper(currentStep = step, totalSteps = 3)
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            
            when (step) {
                0 -> {
                    Text("Step 1: Personal Info")
                    AppTextField(value = "", onValueChange = {}, label = "Full Name")
                    AppTextField(value = "", onValueChange = {}, label = "Father's Name")
                    Spacer(modifier = Modifier.weight(1f))
                    AppButton(text = "NEXT", onClick = { step = 1 })
                }
                1 -> {
                    Text("Step 2: Address Info")
                    AppTextField(value = "", onValueChange = {}, label = "Residential Address")
                    Spacer(modifier = Modifier.weight(1f))
                    AppButton(text = "NEXT", onClick = { step = 2 })
                }
                2 -> {
                    Text("Step 3: Document Upload")
                    Text("Upload Aadhaar & Photo")
                    Spacer(modifier = Modifier.weight(1f))
                    AppButton(text = "SUBMIT", onClick = { step = 3 })
                }
                3 -> {
                    Text("Application Submitted!", style = MaterialTheme.typography.headlineMedium)
                    Text("Ack No: PAN123456789")
                    AppButton(text = "DONE", onClick = onBack)
                }
            }
        }
    }
}
