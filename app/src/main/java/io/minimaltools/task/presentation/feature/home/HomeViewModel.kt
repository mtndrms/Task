package io.minimaltools.task.presentation.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minimaltools.task.data.fake.task.FakeTaskData.getAllFakeTasks
import io.minimaltools.task.data.local.entity.task.Task
import io.minimaltools.task.util.DateUtils
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

    fun createTask(task: Task) {
        _uiState.update { state: HomeUiState ->
            val tasks = state.tasks.toMutableList()
            tasks.add(task)

            state.copy(tasks = tasks)
        }
    }

    fun removeTask(task: Task) {
        _uiState.update { state: HomeUiState ->
            val tasks = state.tasks.toMutableList()
            tasks.remove(task)

            state.copy(tasks = tasks)
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

    fun filterByDateRange(startDate: Long, endDate: Long) {
        val tasks = _uiState.value.tasks.filter {
            DateUtils.dateStringToMilliseconds(it.dueDate) in startDate..endDate
        }

        _uiState.update { state: HomeUiState ->
            state.copy(startDate = startDate, endDate = endDate, tasks = tasks)
        }
    }
}