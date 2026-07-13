package com.app.superapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.superapp.core.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    private val _navigationEvent = MutableSharedFlow<SplashNavigation>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    init {
        checkNavigation()
    }

    private fun checkNavigation() {
        viewModelScope.launch {
            val onboardingCompleted = preferenceManager.onboardingCompleted.first()
            val isAuthenticated = preferenceManager.isAuthenticated.first()

            if (!onboardingCompleted) {
                _navigationEvent.emit(SplashNavigation.Onboarding)
            } else if (!isAuthenticated) {
                _navigationEvent.emit(SplashNavigation.Login)
            } else {
                _navigationEvent.emit(SplashNavigation.Home)
            }
        }
    }

    sealed class SplashNavigation {
        object Onboarding : SplashNavigation()
        object Login : SplashNavigation()
        object Home : SplashNavigation()
    }
}
