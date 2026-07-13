package com.app.superapp.ui.payments

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun PaymentEntryScreen(
    payeeName: String,
    upiId: String,
    onPaymentComplete: (String) -> Unit,
    onBack: () -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "Pay", onBackClick = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Paying to",
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = payeeName,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = upiId,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
            
            AppTextField(
                value = amount,
                onValueChange = { amount = it },
                label = "Amount (₹)"
            )
            
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            
            AppTextField(
                value = note,
                onValueChange = { note = it },
                label = "Add a note (Optional)"
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            AppButton(
                text = "PAY ₹" + (if (amount.isEmpty()) "0" else amount),
                enabled = amount.isNotEmpty(),
                onClick = { onPaymentComplete(amount) }
            )
        }
    }
}
