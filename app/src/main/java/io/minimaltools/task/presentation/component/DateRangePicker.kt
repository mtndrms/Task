package io.minimaltools.task.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.minimaltools.task.util.DateUtils

@Composable
fun DateRangePicker(
    onCompleted: (Long, Long) -> Unit,
    onCancel: () -> Unit
) {
    val startDatePickerDialogVisibilityState = remember { mutableStateOf(true) }
    val endDatePickerDialogVisibilityState = remember { mutableStateOf(false) }

    val startDate = remember { mutableLongStateOf(0L) }
    val endDate = remember { mutableLongStateOf(0L) }

    DatePickerDialog(
        state = startDatePickerDialogVisibilityState,
        onConfirm = { selectedDate ->
            startDate.longValue = selectedDate
            endDatePickerDialogVisibilityState.value = true
        },
        onCancel = onCancel
    )

    DatePickerDialog(
        state = endDatePickerDialogVisibilityState,
        onConfirm = { selectedDate ->
            endDate.longValue = selectedDate
            onCompleted(startDate.longValue, endDate.longValue)
        },
        onCancel = onCancel
    )
}