package com.app.superapp.ui.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.superapp.core.mock.simulateNetworkDelay
import com.app.superapp.core.util.UiState
import com.app.superapp.domain.dashboard.model.ServiceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServicesViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<Map<String, List<ServiceItem>>>>(UiState.Loading)
    val uiState: StateFlow<UiState<Map<String, List<ServiceItem>>>> = _uiState

    init {
        loadServices()
    }

    private fun loadServices() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            simulateNetworkDelay()
            
            val services = mapOf(
                "Banking" to listOf(
                    ServiceItem("1", "AEPS", Icons.Default.Fingerprint, "aeps"),
                    ServiceItem("2", "DMT", Icons.Default.Send, "dmt"),
                    ServiceItem("3", "Mini ATM", Icons.Default.CreditCard, "mini_atm")
                ),
                "Bill Payments" to listOf(
                    ServiceItem("4", "Mobile", Icons.Default.PhoneAndroid, "mobile_recharge"),
                    ServiceItem("5", "DTH", Icons.Default.Tv, "dth"),
                    ServiceItem("6", "Electricity", Icons.Default.Lightbulb, "electricity")
                ),
                "Travel" to listOf(
                    ServiceItem("7", "Bus", Icons.Default.DirectionsBus, "bus_booking"),
                    ServiceItem("8", "Flights", Icons.Default.Flight, "flight_booking")
                ),
                "Govt Services" to listOf(
                    ServiceItem("9", "New PAN", Icons.Default.Assignment, "new_pan"),
                    ServiceItem("10", "GST", Icons.Default.Business, "gst_registration")
                )
            )

            _uiState.value = UiState.Success(services)
        }
    }
}
