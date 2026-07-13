package com.app.superapp.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppOtpInput
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun PinSetupScreen(onNavigate: () -> Unit) {
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var isConfirmStage by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { AppTopBar(title = "Setup PIN") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (!isConfirmStage) "Set your 4-digit PIN" else "Confirm your PIN",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
            
            AppOtpInput(
                value = if (!isConfirmStage) pin else confirmPin,
                onValueChange = { 
                    if (!isConfirmStage) pin = it else confirmPin = it
                    error = null
                },
                length = 4
            )
            
            if (error != null) {
                Text(
                    text = error!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = Dimens.SpacingMedium)
                )
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            AppButton(
                text = if (!isConfirmStage) "CONTINUE" else "SETUP PIN",
                enabled = (if (!isConfirmStage) pin.length else confirmPin.length) == 4,
                onClick = {
                    if (!isConfirmStage) {
                        isConfirmStage = true
                    } else {
                        if (pin == confirmPin) {
                            onNavigate()
                        } else {
                            error = "PINs do not match"
                            confirmPin = ""
                        }
                    }
                }
            )
        }
    }
}
