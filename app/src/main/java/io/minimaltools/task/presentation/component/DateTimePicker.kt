package io.minimaltools.task.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.minimaltools.task.util.DateUtils

@Composable
fun DateTimePicker(
    onCompleted: (Long, String) -> Unit,
    initalVisibilityState: Boolean = false,
    content: @Composable (MutableState<Boolean>) -> Unit = {}
) {
    val datePickerDialogVisibilityState = remember { mutableStateOf(initalVisibilityState) }
    val timePickerDialogVisibilityState = remember { mutableStateOf(false) }

    val date = remember { mutableLongStateOf(0L) }
    val time = remember { mutableStateOf("") }

    content(datePickerDialogVisibilityState)

    DatePickerDialog(
        state = datePickerDialogVisibilityState,
        onConfirm = { selectedDate ->
            date.longValue = selectedDate
            timePickerDialogVisibilityState.value = true
        }
    )

    TimePickerDialog(
        state = timePickerDialogVisibilityState,
        onConfirm = { selectedTime ->
            time.value = selectedTime.ifEmpty { "00:00" }
            onCompleted(date.longValue, time.value)
        }
    )
}
