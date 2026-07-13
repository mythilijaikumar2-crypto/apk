package com.app.superapp.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.superapp.core.components.ServiceGridTile
import com.app.superapp.core.components.TransactionListItem
import com.app.superapp.core.designsystem.Dimens
import com.app.superapp.core.util.UiState
import com.app.superapp.domain.dashboard.model.PromoBanner
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreen(
    onServiceClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { HomeTopBar() }
    ) { padding ->
        when (val state = uiState) {
            is UiState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
            is UiState.Success -> HomeContent(state.data, padding, onServiceClick)
            is UiState.Error -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(state.message) }
            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Column {
                Text("Hello, Agent!", style = MaterialTheme.typography.titleMedium)
                Text("Welcome back", style = MaterialTheme.typography.labelSmall)
            }
        },
        actions = {
            IconButton(onClick = { }) { Icon(Icons.Default.Notifications, null) }
            IconButton(onClick = { }) { Icon(Icons.Default.Person, null) }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    data: HomeData,
    padding: PaddingValues,
    onServiceClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = Dimens.SpacingMedium)
    ) {
        item {
            WalletCard(balance = data.balance)
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
        }

        item {
            Text("Our Services", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            
            // Grid for services - using a fixed height for simplicity in LazyColumn
            Box(modifier = Modifier.height(200.dp)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(data.services) { service ->
                        ServiceGridTile(
                            name = service.name,
                            icon = service.icon,
                            onClick = { onServiceClick(service.route) }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
        }

        item {
            val pagerState = rememberPagerState(pageCount = { data.banners.size })
            HorizontalPager(state = pagerState) { page ->
                BannerItem(banner = data.banners[page])
            }
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Recent Transactions", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                TextButton(onClick = { }) { Text("View All") }
            }
        }

        items(data.transactions) { transaction ->
            TransactionListItem(transaction = transaction)
            Divider(color = MaterialTheme.colorScheme.outlineVariant)
        }
    }
}

@Composable
fun WalletCard(balance: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(Dimens.SpacingLarge)) {
            Text("Wallet Balance", style = MaterialTheme.typography.labelMedium)
            Text(text = balance, style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)) {
                Button(onClick = { }, modifier = Modifier.weight(1f)) { Text("Add Money") }
                OutlinedButton(onClick = { }, modifier = Modifier.weight(1f)) { Text("Withdraw") }
            }
        }
    }
}

@Composable
fun BannerItem(banner: PromoBanner) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = banner.title, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
        }
    }
}
