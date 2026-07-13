package com.app.superapp.ui.banking

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.components.ServiceGridTile
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.domain.dashboard.model.ServiceItem

@Composable
fun BankingDashboardScreen(onServiceClick: (String) -> Unit, onBack: () -> Unit) {
    val bankingServices = listOf(
        ServiceItem("1", "AEPS", Icons.Default.Fingerprint, "aeps"),
        ServiceItem("2", "DMT", Icons.Default.Send, "dmt"),
        ServiceItem("3", "Mini ATM", Icons.Default.CreditCard, "mini_atm"),
        ServiceItem("4", "Cash Deposit", Icons.Default.AccountBalanceWallet, "cash_deposit"),
        ServiceItem("5", "Balance Enquiry", Icons.Default.AccountBalance, "balance_enquiry"),
        ServiceItem("6", "Mini Statement", Icons.Default.ListAlt, "mini_statement")
    )

    Scaffold(
        topBar = { AppTopBar(title = "Banking Services", onBackClick = onBack) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingMedium)
        ) {
            item {
                BusinessSummaryCards()
                Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            }

            item {
                Text("All Banking Services", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
                
                Box(modifier = Modifier.height(200.dp)) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        userScrollEnabled = false
                    ) {
                        items(bankingServices) { service ->
                            ServiceGridTile(
                                name = service.name,
                                icon = service.icon,
                                onClick = { onServiceClick(service.route) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessSummaryCards() {
    Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        SummaryCard(title = "Today's Business", value = "₹45,000", modifier = Modifier.weight(1f))
        SummaryCard(title = "Commission", value = "₹1,250", modifier = Modifier.weight(1f))
    }
}

@Composable
fun SummaryCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(modifier = Modifier.padding(Dimens.SpacingMedium)) {
            Text(text = title, style = MaterialTheme.typography.labelMedium)
            Text(text = value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }
    }
}
