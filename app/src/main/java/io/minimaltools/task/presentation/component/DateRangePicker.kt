package io.minimaltools.task.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.minimaltools.task.util.DateUtils

@Composable
fun DateRangePicker(
    onCompleted: (String, String) -> Unit
) {
    val startDatePickerDialogVisibilityState = remember { mutableStateOf(true) }
    val endDatePickerDialogVisibilityState = remember { mutableStateOf(false) }

    val startDate = remember { mutableStateOf(DateUtils.getPlaceholderDate()) }
    val endDate = remember { mutableStateOf("") }

    DatePickerDialog(
        state = startDatePickerDialogVisibilityState,
        onConfirm = { selectedDate ->
            startDate.value = selectedDate
            endDatePickerDialogVisibilityState.value = true
        }
    )

    DatePickerDialog(
        state = endDatePickerDialogVisibilityState,
        onConfirm = { selectedDate ->
            endDate.value = selectedDate
            onCompleted(startDate.value, endDate.value)
        }
    )
}