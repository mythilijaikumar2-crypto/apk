package com.app.superapp.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.superapp.core.mock.simulateNetworkDelay
import com.app.superapp.core.util.UiState
import com.app.superapp.domain.dashboard.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<HomeData>>(UiState.Loading)
    val uiState: StateFlow<UiState<HomeData>> = _uiState

    init {
        loadDashboard()
    }

    private fun loadDashboard() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            simulateNetworkDelay()
            
            val services = listOf(
                ServiceItem("1", "AEPS", Icons.Default.Face, "aeps"),
                ServiceItem("2", "DMT", Icons.Default.Send, "dmt"),
                ServiceItem("3", "Recharge", Icons.Default.PhoneAndroid, "mobile_recharge"),
                ServiceItem("4", "Electricity", Icons.Default.Warning, "electricity"),
                ServiceItem("5", "Bus", Icons.Default.Place, "bus_booking"),
                ServiceItem("6", "PAN", Icons.Default.AccountBox, "new_pan")
            )
            
            val transactions = listOf(
                Transaction("1", "Wallet Topup", "+₹500.00", "12 Oct, 10:30 AM", "Success"),
                Transaction("2", "Mobile Recharge", "-₹249.00", "11 Oct, 05:20 PM", "Success"),
                Transaction("3", "AEPS Withdrawal", "+₹2000.00", "10 Oct, 11:15 AM", "Pending")
            )
            
            val banners = listOf(
                PromoBanner("1", "", "Get 10% Cashback on Electricity Bills"),
                PromoBanner("2", "", "Free Insurance with every PAN application")
            )

            _uiState.value = UiState.Success(HomeData("₹12,450.50", services, transactions, banners))
        }
    }
}

data class HomeData(
    val balance: String,
    val services: List<ServiceItem>,
    val transactions: List<Transaction>,
    val banners: List<PromoBanner>
)
