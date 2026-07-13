package com.app.superapp.domain.dashboard.model

import androidx.compose.ui.graphics.vector.ImageVector

data class ServiceItem(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val route: String
)

data class Transaction(
    val id: String,
    val title: String,
    val amount: String,
    val date: String,
    val status: String // Success, Pending, Failed
)

data class PromoBanner(
    val id: String,
    val imageUrl: String,
    val title: String
)
