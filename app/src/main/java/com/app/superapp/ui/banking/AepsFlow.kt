package com.app.superapp.ui.banking

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun AepsFlow(onBack: () -> Unit) {
    var currentStep by remember { mutableStateOf(0) }
    var selectedService by remember { mutableStateOf("") }
    var aadhaarNumber by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "AEPS", onBackClick = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge)
        ) {
            when (currentStep) {
                0 -> AepsServiceSelection { service ->
                    selectedService = service
                    currentStep = 1
                }
                1 -> AadhaarEntry(
                    aadhaarNumber, { aadhaarNumber = it },
                    amount, { amount = it },
                    isWithdrawal = selectedService == "Cash Withdrawal",
                    onNext = { currentStep = 2 }
                )
                2 -> FingerprintCapture { currentStep = 3 }
                3 -> AepsReceipt(selectedService, amount, onBack)
            }
        }
    }
}

@Composable
fun AepsServiceSelection(onSelect: (String) -> Unit) {
    val services = listOf("Cash Withdrawal", "Balance Enquiry", "Mini Statement", "Aadhaar Pay")
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        Text("Select Service", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        services.forEach { service ->
            OutlinedButton(
                onClick = { onSelect(service) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(service)
            }
        }
    }
}

@Composable
fun AadhaarEntry(
    aadhaar: String, onAadhaarChange: (String) -> Unit,
    amount: String, onAmountChange: (String) -> Unit,
    isWithdrawal: Boolean,
    onNext: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        Text("Customer Details", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        AppTextField(value = aadhaar, onValueChange = onAadhaarChange, label = "Aadhaar Number (12-digit)")
        if (isWithdrawal) {
            AppTextField(value = amount, onValueChange = onAmountChange, label = "Amount")
        }
        Spacer(modifier = Modifier.weight(1f))
        AppButton(text = "PROCEED TO SCAN", onClick = onNext, enabled = aadhaar.length == 12)
    }
}

@Composable
fun FingerprintCapture(onComplete: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.Fingerprint,
            contentDescription = null,
            modifier = Modifier.size(120.dp).scale(scale),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
        Text("Place finger on the scanner", style = MaterialTheme.typography.titleMedium)
        
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(3000)
            onComplete()
        }
    }
}

@Composable
fun AepsReceipt(service: String, amount: String, onFinish: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Transaction Successful", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        Text("Service: \$service")
        if (amount.isNotEmpty()) Text("Amount: ₹\$amount")
        Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
        AppButton(text = "DONE", onClick = onFinish)
    }
}
