package io.minimaltools.task.presentation.feature.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minimaltools.task.data.fake.task.FakeTaskData.getAllFakeTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getAll()
    }

    private fun getAll() {
        _uiState.update { state: HomeUiState ->
            state.copy(tasks = getAllFakeTasks())
        }
    }

    fun pinTask() {
        _uiState.update { state: HomeUiState ->
            state.copy(shouldDisplayPinnedTaskSnackbar = true)
        }
    }

    fun clearUndoState() {
        _uiState.update { state: HomeUiState ->
            state.copy(shouldDisplayPinnedTaskSnackbar = false)
        }
    }
}