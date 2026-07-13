package com.app.superapp.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppStepper
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun RegistrationScreen(onNavigate: () -> Unit) {
    var currentStep by remember { mutableStateOf(0) }
    
    // Step 1: Personal Details
    var name by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    
    // Step 2: Business Details
    var shopName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var shopType by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "Registration") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge)
        ) {
            AppStepper(currentStep = currentStep, totalSteps = 2)
            
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                if (currentStep == 0) {
                    PersonalDetailsForm(
                        name = name, onNameChange = { name = it },
                        dob = dob, onDobChange = { dob = it },
                        email = email, onEmailChange = { email = it }
                    )
                } else {
                    BusinessDetailsForm(
                        shopName = shopName, onShopNameChange = { shopName = it },
                        address = address, onAddressChange = { address = it },
                        shopType = shopType, onShopTypeChange = { shopType = it }
                    )
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)
            ) {
                if (currentStep > 0) {
                    AppButton(
                        text = "BACK",
                        onClick = { currentStep-- },
                        modifier = Modifier.weight(1f)
                    )
                }
                AppButton(
                    text = if (currentStep == 1) "FINISH" else "NEXT",
                    onClick = {
                        if (currentStep < 1) currentStep++
                        else onNavigate()
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun PersonalDetailsForm(
    name: String, onNameChange: (String) -> Unit,
    dob: String, onDobChange: (String) -> Unit,
    email: String, onEmailChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        AppTextField(value = name, onValueChange = onNameChange, label = "Full Name")
        AppTextField(value = dob, onValueChange = onDobChange, label = "Date of Birth (DD/MM/YYYY)")
        AppTextField(value = email, onValueChange = onEmailChange, label = "Email Address")
    }
}

@Composable
fun BusinessDetailsForm(
    shopName: String, onShopNameChange: (String) -> Unit,
    address: String, onAddressChange: (String) -> Unit,
    shopType: String, onShopTypeChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        AppTextField(value = shopName, onValueChange = onShopNameChange, label = "Shop/Business Name")
        AppTextField(value = address, onValueChange = onAddressChange, label = "Business Address")
        AppTextField(value = shopType, onValueChange = onShopTypeChange, label = "Business Type")
    }
}
