package com.app.superapp.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun EditProfileScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("Agent Name") }
    var email by remember { mutableStateOf("agent@superapp.com") }
    var mobile by remember { mutableStateOf("9876543210") }

    Scaffold(
        topBar = { AppTopBar(title = "Edit Profile", onBackClick = onBack) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingLarge)) {
            AppTextField(value = name, onValueChange = { name = it }, label = "Full Name")
            AppTextField(value = email, onValueChange = { email = it }, label = "Email Address")
            AppTextField(value = mobile, onValueChange = { mobile = it }, label = "Mobile Number")
            Spacer(modifier = Modifier.weight(1f))
            AppButton(text = "SAVE CHANGES", onClick = onBack)
        }
    }
}
