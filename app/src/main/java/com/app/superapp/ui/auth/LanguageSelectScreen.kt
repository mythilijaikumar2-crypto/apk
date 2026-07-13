package com.app.superapp.ui.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.superapp.core.components.AppButton
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.core.navigation.Routes
import com.app.superapp.core.util.PreferenceManager
import kotlinx.coroutines.launch

@Composable
fun LanguageSelectScreen(
    onNavigate: (String) -> Unit,
    viewModel: LanguageViewModel = hiltViewModel()
) {
    val languages = listOf(
        Language("en", "English"),
        Language("ta", "Tamil"),
        Language("hi", "Hindi")
    )
    var selectedLanguage by remember { mutableStateOf(languages[0]) }

    Scaffold(
        topBar = { AppTopBar(title = "Select Language") }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.SpacingMedium)
        ) {
            Text(
                "Choose your preferred language",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = Dimens.SpacingLarge)
            )

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(languages) { language ->
                    LanguageItem(
                        language = language,
                        isSelected = selectedLanguage == language,
                        onSelect = { selectedLanguage = language }
                    )
                }
            }

            AppButton(
                text = "CONTINUE",
                onClick = {
                    viewModel.setLanguage(selectedLanguage.code) {
                        onNavigate(Routes.Login.route)
                    }
                }
            )
        }
    }
}

data class Language(val code: String, val name: String)

@Composable
fun LanguageItem(
    language: Language,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .padding(vertical = Dimens.SpacingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = onSelect)
        Spacer(modifier = Modifier.width(Dimens.SpacingMedium))
        Text(language.name, style = MaterialTheme.typography.bodyLarge)
    }
}
