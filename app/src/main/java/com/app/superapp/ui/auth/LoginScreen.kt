package com.app.superapp.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.core.navigation.Routes

@Composable
fun LoginScreen(onNavigate: (String) -> Unit) {
    var mobileNumber by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { AppTopBar(title = "Login") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge)
        ) {
            Text(
                text = "Enter your mobile number to continue",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            
            AppTextField(
                value = mobileNumber,
                onValueChange = { 
                    if (it.length <= 10) mobileNumber = it 
                    error = null
                },
                label = "Mobile Number",
                error = error
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            AppButton(
                text = "GET OTP",
                onClick = {
                    if (mobileNumber.length == 10) {
                        onNavigate("otp_verification/\$mobileNumber")
                    } else {
                        error = "Enter a valid 10-digit number"
                    }
                }
            )
            
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("New user?")
                TextButton(onClick = { onNavigate(Routes.Register.route) }) {
                    Text("Register Now")
                }
            }
        }
    }
}
