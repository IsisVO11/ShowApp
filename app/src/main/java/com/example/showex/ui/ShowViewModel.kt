package com.example.showex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showex.data.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowViewModel @Inject constructor(
    private val repository: ShowRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShowUiState())
    val uiState: StateFlow<ShowUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getAllShows(),
                repository.getAllCategories()
            ) { shows, categories ->
                ShowUiState(
                    shows = shows,
                    allCategories = categories.distinct()
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun addShow(show: ShowEntity) {
        viewModelScope.launch {
            repository.addShow(show)
        }
    }

    fun deleteShow(show: ShowEntity) {
        viewModelScope.launch {
            repository.deleteShow(show)
        }
    }

    fun getShowById(id: String): Flow<ShowEntity?> = flow {
        emit(repository.getShowById(id))
    }

    fun updateShow(show: ShowEntity) {
        viewModelScope.launch {
            repository.addShow(show)
        }
    }
}

data class ShowUiState(
    val shows: List<ShowEntity> = emptyList(),
    val allCategories: List<String> = emptyList()
) 