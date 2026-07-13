package com.app.superapp.ui.payments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun UpiDashboardScreen(onBack: () -> Unit, onAction: (String) -> Unit) {
    Scaffold(
        topBar = { AppTopBar(title = "UPI Payments", onBackClick = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingMedium)
        ) {
            Text("Quick Actions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)
            ) {
                item { UpiActionCard("Send Money", Icons.Default.Send) { onAction("send") } }
                item { UpiActionCard("Request Money", Icons.Default.CallReceived) { onAction("request") } }
                item { UpiActionCard("Scan & Pay", Icons.Default.QrCodeScanner) { onAction("scan") } }
                item { UpiActionCard("Check Balance", Icons.Default.AccountBalance) { onAction("balance") } }
            }
            
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            Text("Linked Bank Accounts", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            
            BankAccountItem("State Bank of India", "**** 1234")
            BankAccountItem("HDFC Bank", "**** 5678")
        }
    }
}

@Composable
fun UpiActionCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.height(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(Dimens.SpacingMedium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Icon(icon, null)
            Text(title, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
fun BankAccountItem(bankName: String, accNo: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = Dimens.SpacingSmall)
    ) {
        ListItem(
            headlineContent = { Text(bankName) },
            supportingContent = { Text(accNo) },
            leadingContent = { Icon(Icons.Default.AccountBalance, null) },
            trailingContent = { Text("Primary", color = MaterialTheme.colorScheme.primary) }
        )
    }
}
