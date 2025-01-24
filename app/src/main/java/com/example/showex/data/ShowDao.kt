package com.example.showex.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShowDao {
    @Query("SELECT * FROM shows ORDER BY title ASC")
    fun getAllShows(): Flow<List<ShowEntity>>

    @Query("SELECT * FROM shows WHERE id = :id")
    suspend fun getShowById(id: String): ShowEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShow(show: ShowEntity)

    @Delete
    suspend fun deleteShow(show: ShowEntity)

    @Query("SELECT DISTINCT categories FROM shows")
    fun getAllCategories(): Flow<List<String>>
} 