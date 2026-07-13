package com.app.superapp.ui.payments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.components.TransactionListItem
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.domain.dashboard.model.Transaction

@Composable
fun WalletScreen(onBack: () -> Unit) {
    val walletTransactions = listOf(
        Transaction("1", "Added to Wallet", "+₹1000.00", "12 Oct", "Success"),
        Transaction("2", "Paid to Merchant", "-₹450.00", "11 Oct", "Success"),
        Transaction("3", "Refund Received", "+₹50.00", "10 Oct", "Success")
    )

    Scaffold(
        topBar = { AppTopBar(title = "My Wallet", onBackClick = onBack) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                WalletBalanceHeader()
            }
            item {
                Text(
                    "Recent Activity",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(Dimens.SpacingMedium)
                )
            }
            items(walletTransactions) { tx ->
                TransactionListItem(tx)
                Divider(modifier = Modifier.padding(horizontal = Dimens.SpacingMedium))
            }
        }
    }
}

@Composable
fun WalletBalanceHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.SpacingMedium),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(Dimens.SpacingLarge)) {
            Text("Total Balance", style = MaterialTheme.typography.labelLarge)
            Text("₹12,450.50", style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
                Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("Add Money") }
                Button(onClick = {}, modifier = Modifier.weight(1f)) { Text("Transfer") }
            }
        }
    }
}
