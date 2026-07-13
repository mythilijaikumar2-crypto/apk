package com.app.superapp.ui.payments

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun QrScannerScreen(
    onScanResult: (String, String) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { AppTopBar(title = "Scan QR", onBackClick = onBack) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black)
        ) {
            // Mock Viewfinder
            ScannerOverlay()

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(Dimens.SpacingLarge),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Scan any QR code to pay",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
                
                Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingLarge)
                ) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.FlashlightOn, "Flash", tint = Color.White)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Image, "Gallery", tint = Color.White)
                    }
                }
                
                Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
                
                AppButton(
                    text = "MOCK SCAN SUCCESS",
                    onClick = { onScanResult("Kirana Store", "kirana@upi") }
                )
            }
        }
    }
}

@Composable
fun ScannerOverlay() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val strokeWidth = 4.dp.toPx()
        val boxSize = 250.dp.toPx()
        val left = (size.width - boxSize) / 2
        val top = (size.height - boxSize) / 2.5f
        
        // Draw corners
        drawRect(
            color = Color.White,
            topLeft = androidx.compose.ui.geometry.Offset(left, top),
            size = androidx.compose.ui.geometry.Size(boxSize, boxSize),
            style = Stroke(width = strokeWidth)
        )
    }
}
