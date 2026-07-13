package com.app.superapp.ui.banking

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.components.TransactionListItem
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.domain.dashboard.model.Transaction

@Composable
fun TransactionHistoryScreen(onBack: () -> Unit) {
    val transactions = listOf(
        Transaction("1", "AEPS Withdrawal", "+₹2000.00", "12 Oct, 10:30 AM", "Success"),
        Transaction("2", "DMT Transfer", "-₹5000.00", "11 Oct, 05:20 PM", "Success"),
        Transaction("3", "Mobile Recharge", "-₹249.00", "10 Oct, 11:15 AM", "Failed")
    )

    Scaffold(
        topBar = { 
            AppTopBar(
                title = "Transaction History", 
                onBackClick = onBack,
                actions = { IconButton(onClick = { }) { Icon(Icons.Default.FilterList, null) } }
            ) 
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(Dimens.SpacingMedium)
        ) {
            items(transactions) { transaction ->
                TransactionListItem(transaction = transaction)
                Divider(color = MaterialTheme.colorScheme.outlineVariant)
            }
        }
    }
}
