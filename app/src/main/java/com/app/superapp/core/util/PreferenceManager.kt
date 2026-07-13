package com.app.superapp.core.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class PreferenceManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val IS_AUTHENTICATED = booleanPreferencesKey("is_authenticated")
    private val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    private val THEME_MODE = stringPreferencesKey("theme_mode")
    private val LANGUAGE = stringPreferencesKey("language")

    val isAuthenticated: Flow<Boolean> = context.dataStore.data.map { it[IS_AUTHENTICATED] ?: false }
    val onboardingCompleted: Flow<Boolean> = context.dataStore.data.map { it[ONBOARDING_COMPLETED] ?: false }
    val themeMode: Flow<String> = context.dataStore.data.map { it[THEME_MODE] ?: "LIGHT" }
    val language: Flow<String> = context.dataStore.data.map { it[LANGUAGE] ?: "en" }

    suspend fun setAuthenticated(value: Boolean) {
        context.dataStore.edit { it[IS_AUTHENTICATED] = value }
    }

    suspend fun setOnboardingCompleted(value: Boolean) {
        context.dataStore.edit { it[ONBOARDING_COMPLETED] = value }
    }

    suspend fun setThemeMode(value: String) {
        context.dataStore.edit { it[THEME_MODE] = value }
    }

    suspend fun setLanguage(value: String) {
        context.dataStore.edit { it[LANGUAGE] = value }
    }
}
