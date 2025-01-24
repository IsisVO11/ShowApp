package com.example.showex.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.showex.data.ShowEntity
import com.example.showex.data.WatchStatus
import com.example.showex.data.ShowRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditShowScreen(
    modifier: Modifier = Modifier,
    show: ShowEntity? = null,
    onSave: (ShowEntity) -> Unit,
    onNavigateBack: () -> Unit
) {
    var title by remember { mutableStateOf(show?.title ?: "") }
    var status by remember { mutableStateOf(show?.status ?: WatchStatus.PLAN_TO_WATCH) }
    var currentEpisode by remember { mutableStateOf(show?.currentEpisode?.toString() ?: "0") }
    var totalEpisodes by remember { mutableStateOf(show?.totalEpisodes?.toString() ?: "") }
    var currentSeason by remember { mutableStateOf(show?.currentSeason?.toString() ?: "1") }
    var totalSeasons by remember { mutableStateOf(show?.totalSeasons?.toString() ?: "") }
    var rating by remember { mutableStateOf(show?.rating ?: 0f) }
    var categories by remember { mutableStateOf(show?.categories?.joinToString(",") ?: "") }
    var notes by remember { mutableStateOf(show?.notes ?: "") }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (show == null) "Adicionar Show" else "Editar Show") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            val newShow = ShowEntity(
                                id = show?.id ?: ShowRepository.generateShowId(),
                                title = title,
                                status = status,
                                currentEpisode = currentEpisode.toIntOrNull() ?: 0,
                                totalEpisodes = totalEpisodes.toIntOrNull(),
                                currentSeason = currentSeason.toIntOrNull() ?: 1,
                                totalSeasons = totalSeasons.toIntOrNull(),
                                rating = rating,
                                categories = categories.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                                notes = notes
                            )
                            onSave(newShow)
                        },
                        enabled = title.isNotEmpty()
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Salvar")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp)
                .imePadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("Status", style = MaterialTheme.typography.labelLarge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WatchStatus.values().forEach { watchStatus ->
                    FilterChip(
                        selected = status == watchStatus,
                        onClick = { status = watchStatus },
                        label = {
                            Text(
                                when (watchStatus) {
                                    WatchStatus.WATCHING -> "Assistindo"
                                    WatchStatus.PLAN_TO_WATCH -> "Planejo Assistir"
                                    WatchStatus.FINISHED -> "Finalizado"
                                }
                            )
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = currentSeason,
                    onValueChange = { currentSeason = it },
                    label = { Text("Temporada Atual") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = totalSeasons,
                    onValueChange = { totalSeasons = it },
                    label = { Text("Total de Temporadas") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = currentEpisode,
                    onValueChange = { currentEpisode = it },
                    label = { Text("Episódio Atual") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )

                OutlinedTextField(
                    value = totalEpisodes,
                    onValueChange = { totalEpisodes = it },
                    label = { Text("Total de Episódios") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            Text("Avaliação", style = MaterialTheme.typography.labelLarge)
            Slider(
                value = rating,
                onValueChange = { rating = it },
                valueRange = 0f..5f,
                steps = 4
            )
            Text("${rating.toInt()} estrelas")

            OutlinedTextField(
                value = categories,
                onValueChange = { categories = it },
                label = { Text("Categorias (separadas por vírgula)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notas") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
        }
    }
} 