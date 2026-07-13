package com.app.superapp.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.superapp.core.components.AppSearchBar
import com.app.superapp.core.components.ServiceGridTile
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.core.util.UiState
import com.app.superapp.domain.dashboard.model.ServiceItem

@Composable
fun ServicesScreen(
    onServiceClick: (String) -> Unit,
    viewModel: ServicesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        AppSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            placeholder = "Search services..."
        )
        
        when (val state = uiState) {
            is UiState.Loading -> Box(Modifier.fillMaxSize()) { CircularProgressIndicator() }
            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(Dimens.SpacingMedium)
                ) {
                    state.data.forEach { (category, services) ->
                        val filteredServices = services.filter { it.name.contains(searchQuery, ignoreCase = true) }
                        if (filteredServices.isNotEmpty()) {
                            item {
                                Text(
                                    text = category,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(vertical = Dimens.SpacingMedium)
                                )
                            }
                            item {
                                Box(modifier = Modifier.height(((filteredServices.size + 2) / 3 * 100).dp)) {
                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(3),
                                        modifier = Modifier.fillMaxSize(),
                                        userScrollEnabled = false
                                    ) {
                                        items(filteredServices) { service ->
                                            ServiceGridTile(
                                                name = service.name,
                                                icon = service.icon,
                                                onClick = { onServiceClick(service.route) }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else -> {}
        }
    }
}
