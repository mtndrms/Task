package io.minimaltools.task.data

data class UiState<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean = false
)