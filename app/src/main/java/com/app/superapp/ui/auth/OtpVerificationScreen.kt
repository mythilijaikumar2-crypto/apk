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
fun OtpVerificationScreen(
    mobileNumber: String,
    onNavigate: (String) -> Unit
) {
    var otp by remember { mutableStateOf("") }
    var timeLeft by remember { mutableStateOf(30) }

    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            kotlinx.coroutines.delay(1000)
            timeLeft--
        }
    }

    Scaffold(
        topBar = { AppTopBar(title = "Verify OTP", onBackClick = { }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Verify OTP",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Sent to +91 \$mobileNumber",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
            
            AppOtpInput(
                value = otp,
                onValueChange = { otp = it }
            )
            
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            
            if (timeLeft > 0) {
                Text("Resend OTP in \$timeLeft seconds")
            } else {
                TextButton(onClick = { timeLeft = 30 }) {
                    Text("RESEND OTP")
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            AppButton(
                text = "VERIFY",
                enabled = otp.length == 6,
                onClick = { onNavigate("pin_setup") }
            )
        }
    }
}
