package com.example.showex.data

import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

class ShowRepository @Inject constructor(
    private val showDao: ShowDao
) {
    fun getAllShows(): Flow<List<ShowEntity>> = showDao.getAllShows()

    suspend fun getShowById(id: String): ShowEntity? = showDao.getShowById(id)

    suspend fun addShow(show: ShowEntity) = showDao.insertShow(show)

    suspend fun deleteShow(show: ShowEntity) = showDao.deleteShow(show)

    fun getAllCategories(): Flow<List<String>> = showDao.getAllCategories()

    companion object {
        fun generateShowId() = UUID.randomUUID().toString()
    }
} 