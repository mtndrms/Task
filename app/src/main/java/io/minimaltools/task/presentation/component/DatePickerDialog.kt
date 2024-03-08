package io.minimaltools.task.presentation.component

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import io.minimaltools.task.util.DateUtils
import io.minimaltools.task.util.dismiss
import io.minimaltools.task.util.isVisible

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    state: MutableState<Boolean>,
    onConfirm: (date: Long) -> Unit,
    onCancel: () -> Unit = {}
) {
    val datePickerState = rememberDatePickerState()

    if (state.isVisible()) {
        DatePickerDialog(
            onDismissRequest = {
                state.dismiss()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm(
                            datePickerState.selectedDateMillis ?: 0L
                        )
                        state.dismiss()
                    }
                ) {
                    Text(text = "Done")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onCancel()
                        state.dismiss()
                    }
                ) {
                    Text(text = "Cancel")
                }
            },
            content = {
                DatePicker(state = datePickerState)
            }
        )
    }
}