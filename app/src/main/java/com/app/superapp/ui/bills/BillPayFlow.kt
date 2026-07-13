package com.app.superapp.ui.bills

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun BillPayFlow(
    category: String,
    onBack: () -> Unit
) {
    var step by remember { mutableStateOf(0) }
    var selectedProvider by remember { mutableStateOf<String?>(null) }
    var accountId by remember { mutableStateOf("") }
    var billAmount by remember { mutableStateOf("0") }

    Scaffold(
        topBar = { AppTopBar(title = category, onBackClick = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge)
        ) {
            when (step) {
                0 -> ProviderSelection(category) { 
                    selectedProvider = it
                    step = 1 
                }
                1 -> AccountEntry(category, selectedProvider!!) { id ->
                    accountId = id
                    billAmount = (100..2000).random().toString() // Mock bill fetch
                    step = 2
                }
                2 -> BillConfirm(selectedProvider!!, accountId, billAmount) {
                    step = 3
                }
                3 -> BillSuccess(category, billAmount, onBack)
            }
        }
    }
}

@Composable
fun ProviderSelection(category: String, onSelect: (String) -> Unit) {
    val providers = when(category) {
        "Mobile Recharge" -> listOf("Airtel", "Jio", "Vi", "BSNL")
        "DTH" -> listOf("Tata Play", "Airtel DTH", "Dish TV", "Sun Direct")
        "Electricity" -> listOf("TANGEDCO", "BESCOM", "Adani Electricity")
        else -> listOf("Provider 1", "Provider 2")
    }

    Column {
        Text("Select Operator", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        LazyColumn {
            items(providers) { provider ->
                ListItem(
                    headlineContent = { Text(provider) },
                    modifier = Modifier.clickable { onSelect(provider) }
                )
                Divider()
            }
        }
    }
}

@Composable
fun AccountEntry(category: String, provider: String, onNext: (String) -> Unit) {
    var id by remember { mutableStateOf("") }
    val label = if (category == "Mobile Recharge") "Mobile Number" else "Consumer Number / ID"

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        Text("Enter Details", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Text("Operator: \$provider")
        AppTextField(value = id, onValueChange = { id = it }, label = label)
        Spacer(modifier = Modifier.weight(1f))
        AppButton(text = "FETCH BILL", onClick = { onNext(id) }, enabled = id.isNotEmpty())
    }
}

@Composable
fun BillConfirm(provider: String, id: String, amount: String, onConfirm: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        Text("Confirm Payment", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(Dimens.SpacingMedium)) {
                Text("Operator: \$provider")
                Text("ID: \$id")
                Text("Bill Amount: ₹\$amount", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        AppButton(text = "PAY NOW", onClick = onConfirm)
    }
}

@Composable
fun BillSuccess(category: String, amount: String, onDone: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Payment Successful", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Text("\$category of ₹\$amount completed.")
        Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
        AppButton(text = "DONE", onClick = onDone)
    }
}
