package com.app.superapp.ui.travel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun BusBookingFlow(onBack: () -> Unit) {
    var step by remember { mutableStateOf(0) }
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "Bus Booking", onBackClick = onBack) }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(Dimens.SpacingLarge)) {
            when (step) {
                0 -> {
                    AppTextField(value = from, onValueChange = { from = it }, label = "From City")
                    AppTextField(value = to, onValueChange = { to = it }, label = "To City")
                    Spacer(modifier = Modifier.weight(1f))
                    AppButton(text = "SEARCH BUSES", onClick = { step = 1 })
                }
                1 -> {
                    Text("Available Buses")
                    LazyColumn {
                        items(listOf("Srs Travels", "Parveen Travels", "KPN")) { bus ->
                            ListItem(headlineContent = { Text(bus) }, trailingContent = { Text("₹800") }, modifier = Modifier.padding(vertical = Dimens.SpacingSmall))
                            Divider()
                        }
                    }
                    AppButton(text = "SELECT SEAT (MOCK)", onClick = { step = 2 })
                }
                2 -> {
                    Text("Booking Successful!", style = MaterialTheme.typography.headlineMedium)
                    AppButton(text = "DONE", onClick = onBack)
                }
            }
        }
    }
}
