package com.app.superapp.ui.banking

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
fun DmtFlow(onBack: () -> Unit) {
    var currentStep by remember { mutableStateOf(0) }
    var selectedBeneficiary by remember { mutableStateOf<Beneficiary?>(null) }

    Scaffold(
        topBar = { AppTopBar(title = "Money Transfer (DMT)", onBackClick = onBack) },
        floatingActionButton = {
            if (currentStep == 0) {
                FloatingActionButton(onClick = { currentStep = 1 }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Beneficiary")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge)
        ) {
            when (currentStep) {
                0 -> BeneficiaryList { bene ->
                    selectedBeneficiary = bene
                    currentStep = 2
                }
                1 -> AddBeneficiaryForm { currentStep = 0 }
                2 -> SendMoneyForm(selectedBeneficiary!!) { currentStep = 3 }
                3 -> DmtReceipt(selectedBeneficiary!!, onBack)
            }
        }
    }
}

@Composable
fun BeneficiaryList(onSelect: (Beneficiary) -> Unit) {
    val beneficiaries = listOf(
        Beneficiary("1", "John Doe", "1234567890", "SBI", "SBIN0001234"),
        Beneficiary("2", "Jane Smith", "0987654321", "HDFC", "HDFC0005678")
    )

    Column {
        Text("Select Beneficiary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingSmall)) {
            items(beneficiaries) { bene ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { onSelect(bene) },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    ListItem(
                        headlineContent = { Text(bene.name) },
                        supportingContent = { Text("\${bene.bankName} - \${bene.accountNumber}") },
                        trailingContent = { Icon(Icons.Default.Add, null) }
                    )
                }
            }
        }
    }
}

@Composable
fun AddBeneficiaryForm(onAdded: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var account by remember { mutableStateOf("") }
    var ifsc by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        Text("Add New Beneficiary", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        AppTextField(value = name, onValueChange = { name = it }, label = "Beneficiary Name")
        AppTextField(value = account, onValueChange = { account = it }, label = "Account Number")
        AppTextField(value = ifsc, onValueChange = { ifsc = it }, label = "IFSC Code")
        Spacer(modifier = Modifier.weight(1f))
        AppButton(text = "ADD BENEFICIARY", onClick = onAdded)
    }
}

@Composable
fun SendMoneyForm(bene: Beneficiary, onSent: () -> Unit) {
    var amount by remember { mutableStateOf("") }
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        Text("Send Money to \${bene.name}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Bank: \${bene.bankName} (\${bene.ifsc})")
        AppTextField(value = amount, onValueChange = { amount = it }, label = "Amount")
        Spacer(modifier = Modifier.weight(1f))
        AppButton(text = "TRANSFER NOW", onClick = onSent)
    }
}

@Composable
fun DmtReceipt(bene: Beneficiary, onFinish: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Transfer Successful", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        Text("To: \${bene.name}")
        Text("Account: \${bene.accountNumber}")
        Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
        AppButton(text = "DONE", onClick = onFinish)
    }
}

data class Beneficiary(val id: String, val name: String, val accountNumber: String, val bankName: String, val ifsc: String)
