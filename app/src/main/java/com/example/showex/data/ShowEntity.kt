package com.example.showex.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "shows")
data class ShowEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val status: WatchStatus,
    val currentEpisode: Int,
    val totalEpisodes: Int?,
    val currentSeason: Int,
    val totalSeasons: Int?,
    val rating: Float,
    val categories: List<String>,
    val notes: String
)

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String?): List<String> {
        if (value == null) return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromWatchStatus(value: WatchStatus) = value.name

    @TypeConverter
    fun toWatchStatus(value: String) = enumValueOf<WatchStatus>(value)
} 