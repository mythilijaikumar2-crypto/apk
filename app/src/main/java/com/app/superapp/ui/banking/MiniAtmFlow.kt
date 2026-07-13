package com.app.superapp.ui.banking

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTextField
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun MiniAtmFlow(onBack: () -> Unit) {
    var currentStep by remember { mutableStateOf(0) }
    var amount by remember { mutableStateOf("") }

    Scaffold(
        topBar = { AppTopBar(title = "Mini ATM", onBackClick = onBack) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingLarge)
        ) {
            when (currentStep) {
                0 -> MiniAtmAmountEntry(amount, { amount = it }, { currentStep = 1 })
                1 -> CardInsertAnimation { currentStep = 2 }
                2 -> PinEntryPad { currentStep = 3 }
                3 -> MiniAtmReceipt(amount, onBack)
            }
        }
    }
}

@Composable
fun MiniAtmAmountEntry(amount: String, onAmountChange: (String) -> Unit, onNext: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
        Text("Mini ATM Withdrawal", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        AppTextField(value = amount, onValueChange = onAmountChange, label = "Enter Amount")
        Spacer(modifier = Modifier.weight(1f))
        AppButton(text = "PROCEED", onClick = onNext)
    }
}

@Composable
fun CardInsertAnimation(onComplete: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition()
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.CreditCard,
            contentDescription = null,
            modifier = Modifier.size(150.dp).offset(y = offsetY.dp),
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text("Insert or Swipe Card", style = MaterialTheme.typography.titleMedium)
        
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(3000)
            onComplete()
        }
    }
}

@Composable
fun PinEntryPad(onComplete: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Enter ATM PIN", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
        
        // Mock PIN Pad
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
            repeat(4) { Box(modifier = Modifier.size(20.dp).background(Color.Gray, shape = androidx.compose.foundation.shape.CircleShape)) }
        }
        
        Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
        
        AppButton(text = "CONFIRM PIN", onClick = onComplete)
    }
}

@Composable
fun MiniAtmReceipt(amount: String, onFinish: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Withdrawal Successful", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        Text("Amount: ₹\$amount")
        Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
        AppButton(text = "DONE", onClick = onFinish)
    }
}
