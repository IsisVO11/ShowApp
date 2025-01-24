package com.example.showex.data

enum class WatchStatus {
    WATCHING,
    PLAN_TO_WATCH,
    FINISHED
}

data class Show(
    val id: String = "",
    val title: String,
    val status: WatchStatus,
    val currentEpisode: Int = 0,
    val totalEpisodes: Int? = null,
    val rating: Float = 0f,
    val categories: List<String> = emptyList(),
    val tags: List<String> = emptyList(),
    val notes: String = ""
) 