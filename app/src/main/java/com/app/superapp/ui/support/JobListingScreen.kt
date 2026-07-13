package com.app.superapp.ui.support

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.app.superapp.core.components.AppTopBar
import com.app.superapp.core.designsystem.Dimens

@Composable
fun JobListingScreen(onBack: () -> Unit) {
    val jobs = listOf(
        Job("1", "Sales Agent", "Super App Corp", "Chennai", "₹20,000 - ₹30,000"),
        Job("2", "Delivery Partner", "Fast Delivery", "Madurai", "₹15,000 - ₹25,000"),
        Job("3", "Support Executive", "BPO Services", "Coimbatore", "₹18,000 - ₹22,000")
    )

    Scaffold(
        topBar = { AppTopBar(title = "Jobs Near You", onBackClick = onBack) }
    ) { padding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(padding)) {
            items(jobs) { job ->
                JobCard(job)
            }
        }
    }
}

@Composable
fun JobCard(job: Job) {
    Card(modifier = Modifier.fillMaxWidth().padding(Dimens.SpacingMedium)) {
        Column(modifier = Modifier.padding(Dimens.SpacingMedium)) {
            Text(job.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(job.company, style = MaterialTheme.typography.bodyLarge)
            Text("\${job.location} | \${job.salary}", color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
            Button(onClick = {}) { Text("APPLY NOW") }
        }
    }
}

data class Job(val id: String, val title: String, val company: String, val location: String, val salary: String)
