package com.app.superapp.ui.banking

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun CashDepositFlow(onBack: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    var account by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Scaffold(topBar = { AppTopBar(title = "Cash Deposit", onBackClick = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingLarge)) {
            if (step == 0) {
                Text("Deposit Cash", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                AppTextField(value = account, onValueChange = { account = it }, label = "Account Number")
                AppTextField(value = amount, onValueChange = { amount = it }, label = "Amount")
                Spacer(modifier = Modifier.weight(1f))
                AppButton(text = "DEPOSIT", onClick = { step = 1 })
            } else {
                Text("Deposit Successful", style = MaterialTheme.typography.headlineMedium)
                Text("To Account: \$account")
                Text("Amount: ₹\$amount")
                AppButton(text = "DONE", onClick = onBack)
            }
        }
    }
}

@Composable
fun BalanceEnquiryFlow(onBack: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    var aadhaar by remember { mutableStateOf("") }

    Scaffold(topBar = { AppTopBar(title = "Balance Enquiry", onBackClick = onBack) }) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingLarge)) {
            if (step == 0) {
                AppTextField(value = aadhaar, onValueChange = { aadhaar = it }, label = "Aadhaar Number")
                AppButton(text = "CHECK BALANCE", onClick = { step = 1 })
            } else {
                Text("Current Balance", style = MaterialTheme.typography.titleMedium)
                Text("₹24,560.00", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                AppButton(text = "BACK", onClick = onBack)
            }
        }
    }
}
