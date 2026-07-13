package com.app.superapp.core.components

import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun SecurityDialog(
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var pin by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Enter Security PIN") },
        text = {
            OutlinedTextField(
                value = pin,
                onValueChange = { pin = it },
                label = { Text("PIN") }
            )
        },
        confirmButton = {
            Button(onClick = { onConfirm(pin) }) { Text("CONFIRM") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("CANCEL") }
        }
    )
}
