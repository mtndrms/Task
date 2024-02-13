package io.minimaltools.task.presentation.feature.home

import io.minimaltools.task.data.local.entity.task.Task

data class HomeUiState(
    val tasks: List<Task> = emptyList(),
    val shouldDisplayPinnedTaskSnackbar: Boolean = false,
    var startDate: String = "",
    var startTime: String = "",
    var endDate: String = "",
    var endTime: String = ""
)
