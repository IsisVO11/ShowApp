package com.example.showex.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.showex.data.ShowEntity
import com.example.showex.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDetailsScreen(
    show: ShowEntity,
    onNavigateBack: () -> Unit,
    onEditShow: () -> Unit,
    onDeleteShow: () -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(show.title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(onClick = onEditShow) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                    }
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Default.Delete, contentDescription = "Excluir")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            StatusChip(status = show.status)
            
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Progresso",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Episódio ${show.currentEpisode} de ${show.totalEpisodes ?: "?"}"
                    )
                    if (show.totalEpisodes != null) {
                        LinearProgressIndicator(
                            progress = show.currentEpisode.toFloat() / show.totalEpisodes,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            if (show.rating > 0) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Avaliação",
                            style = MaterialTheme.typography.titleMedium
                        )
                        RatingBar(rating = show.rating)
                    }
                }
            }

            if (show.categories.isNotEmpty()) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Categorias",
                            style = MaterialTheme.typography.titleMedium
                        )
                        CategoryRow(categories = show.categories)
                    }
                }
            }

            if (show.notes.isNotEmpty()) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Notas",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(show.notes)
                    }
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Confirmar exclusão") },
            text = { Text("Tem certeza que deseja excluir ${show.title}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDeleteShow()
                    }
                ) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
} 