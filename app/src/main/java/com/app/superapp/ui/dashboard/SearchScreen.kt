package com.app.superapp.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.app.superapp.core.components.AppSearchBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun SearchScreen(onNavigate: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    val recentSearches = listOf("AEPS", "Mobile Recharge", "Electricity")
    val suggestions = listOf("Bus Booking", "Flight Booking", "Insurance")

    Column(modifier = Modifier.fillMaxSize()) {
        AppSearchBar(
            query = query,
            onQueryChange = { query = it },
            placeholder = "Search for services, transactions..."
        )
        
        if (query.isEmpty()) {
            RecentSearches(recentSearches)
        } else {
            SearchResults(query, suggestions, onNavigate)
        }
    }
}

@Composable
fun RecentSearches(searches: List<String>) {
    Column(modifier = Modifier.padding(Dimens.SpacingMedium)) {
        Text("Recent Searches", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
        searches.forEach { search ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = Dimens.SpacingSmall),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)
            ) {
                Icon(Icons.Default.History, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(search)
            }
        }
    }
}

@Composable
fun SearchResults(query: String, results: List<String>, onNavigate: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(results) { result ->
            ListItem(
                headlineContent = { Text(result) },
                modifier = Modifier.padding(Dimens.SpacingSmall)
            )
        }
    }
}
