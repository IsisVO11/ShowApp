package com.example.showex.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.showex.data.Show
import com.example.showex.data.ShowEntity
import com.example.showex.data.WatchStatus
import com.example.showex.ui.theme.AccentPurple
import com.example.showex.ui.theme.FinishedPurple
import com.example.showex.ui.theme.LavenderPurple
import com.example.showex.ui.theme.PlanToWatchBlue
import com.example.showex.ui.theme.WatchingGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAddShow: () -> Unit,
    shows: List<ShowEntity>,
    onShowClick: (ShowEntity) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Meus Shows") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddShow,
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Show")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(shows) { show ->
                ShowCard(
                    show = show,
                    onClick = { onShowClick(show) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowCard(
    show: ShowEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = show.title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatusChip(status = show.status)
                Text(
                    text = "Ep: ${show.currentEpisode}/${show.totalEpisodes ?: "?"}"
                )
            }
            
            if (show.rating > 0) {
                Spacer(modifier = Modifier.height(8.dp))
                RatingBar(rating = show.rating)
            }
            
            if (show.categories.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                CategoryRow(categories = show.categories)
            }
        }
    }
}

@Composable
fun StatusChip(status: WatchStatus) {
    val (backgroundColor, text) = when (status) {
        WatchStatus.WATCHING -> WatchingGreen to "Assistindo"
        WatchStatus.PLAN_TO_WATCH -> PlanToWatchBlue to "Planejo Assistir"
        WatchStatus.FINISHED -> FinishedPurple to "Finalizado"
    }
    
    Surface(
        color = backgroundColor,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun RatingBar(rating: Float) {
    Row {
        repeat(5) { index ->
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (index < rating) AccentPurple else MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
fun CategoryRow(categories: List<String>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        categories.forEach { category ->
            Surface(
                color = LavenderPurple,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = category,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
} 