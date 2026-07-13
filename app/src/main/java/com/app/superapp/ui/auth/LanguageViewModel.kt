package com.app.superapp.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.superapp.core.util.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    fun setLanguage(code: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            preferenceManager.setLanguage(code)
            preferenceManager.setOnboardingCompleted(true)
            onSuccess()
        }
    }
}
