package com.app.superapp.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.domain.dashboard.model.Transaction

@Composable
fun TransactionListItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.SpacingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = transaction.title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = transaction.date,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = transaction.amount,
                style = MaterialTheme.typography.bodyLarge,
                color = if (transaction.amount.startsWith("-")) MaterialTheme.colorScheme.error 
                        else MaterialTheme.colorScheme.primary
            )
            StatusBadge(status = transaction.status)
        }
    }
}

@Composable
fun StatusBadge(status: String) {
    val color = when (status) {
        "Success" -> MaterialTheme.colorScheme.primary // Using primary for Success for now
        "Pending" -> MaterialTheme.colorScheme.tertiary
        "Failed" -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    Text(
        text = status,
        style = MaterialTheme.typography.labelSmall,
        color = color
    )
}
