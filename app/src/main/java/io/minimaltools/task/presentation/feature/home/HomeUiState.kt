package io.minimaltools.task.presentation.feature.home

import io.minimaltools.task.data.fake.task.Task

data class HomeUiState(
    val tasks: List<Task> = emptyList(),
    val shouldDisplayPinnedTaskSnackbar: Boolean = false
)
