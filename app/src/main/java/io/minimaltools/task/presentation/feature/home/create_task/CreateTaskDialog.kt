package io.minimaltools.task.presentation.feature.home.create_task

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import io.minimaltools.task.R
import io.minimaltools.task.data.fake.group.FakeTaskGroupData
import io.minimaltools.task.data.local.entity.task.priority.Priority
import io.minimaltools.task.presentation.common.AppIcons
import io.minimaltools.task.presentation.theme.AppTheme
import io.minimaltools.task.util.DateUtils
import io.minimaltools.task.util.capitalize
import io.minimaltools.task.util.dismissDialog
import io.minimaltools.task.util.isVisible

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskDialog(dismissDialog: () -> Unit) {
    var title by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { dismissDialog() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                TopAppBar(
                    title = { Text(text = "New Task") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                dismissDialog()
                            },
                            content = {
                                Icon(
                                    imageVector = AppIcons.Close,
                                    contentDescription = stringResource(R.string.navigate_back)
                                )
                            }
                        )
                    },
                    actions = {
                        TextButton(
                            onClick = {
                                dismissDialog()
                            },
                            content = {
                                Text(
                                    text = stringResource(id = R.string.create),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        )
                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 5.dp)
                ) {
                    SectionTitle(title = stringResource(id = R.string.title))
                    TextField(
                        value = title, onValueChange = { title = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(height = 25.dp))

                    SectionTitle(title = stringResource(id = R.string.description))
                    TextField(
                        value = title, onValueChange = { title = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(125.dp)
                    )
                    Spacer(modifier = Modifier.height(height = 25.dp))

                    SectionTitle(title = stringResource(R.string.due_date))
                    DateTimePicker()
                    Spacer(modifier = Modifier.height(height = 25.dp))

                    SectionTitle(title = stringResource(id = R.string.priority))
                    PriorityDropdownMenu(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(height = 25.dp))

                    SectionTitle(title = stringResource(id = R.string.task_group))
                    TaskGroupDropdownMenu(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = Modifier
            .padding(start = 5.dp)
            .then(modifier)
    )
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
private fun DateTimePicker() {
    val datePickerDialogVisibilityState = remember { mutableStateOf(false) }
    val timePickerDialogVisibilityState = remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 10.dp)
            .clickable {
                datePickerDialogVisibilityState.value = true
            }
    ) {
        Text(
            text = "${selectedDate.ifEmpty { DateUtils.getPlaceholderDate() }} - ${selectedTime.ifEmpty { "00:00" }}",
            style = MaterialTheme.typography.bodyLarge
        )
    }

    DatePicker(
        state = datePickerDialogVisibilityState,
        onConfirm = {
            selectedDate = it
            timePickerDialogVisibilityState.value = true
        }
    )

    TimePicker(
        state = timePickerDialogVisibilityState,
        onConfirm = { selectedTime = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DatePicker(
    state: MutableState<Boolean>,
    onConfirm: (date: String) -> Unit
) {
    val datePickerState = rememberDatePickerState()

    if (state.isVisible()) {
        DatePickerDialog(
            onDismissRequest = {
                state.dismissDialog()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirm(
                            DateUtils.millisecondsToDateString(
                                datePickerState.selectedDateMillis ?: 0L
                            )
                        )
                        state.dismissDialog()
                    }
                ) {
                    Text(text = "Done")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        state.dismissDialog()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimePicker(state: MutableState<Boolean>, onConfirm: (time: String) -> Unit) {
    val timePickerState = rememberTimePickerState()

    if (state.isVisible()) {
        Dialog(
            onDismissRequest = { state.dismissDialog() },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            ),
        ) {
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 6.dp,
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .background(
                        shape = MaterialTheme.shapes.extraLarge,
                        color = MaterialTheme.colorScheme.surface
                    ),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        text = "Select date",
                        style = MaterialTheme.typography.labelMedium
                    )

                    androidx.compose.material3.TimePicker(state = timePickerState)

                    Row(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        TextButton(
                            onClick = { state.dismissDialog() }
                        ) {
                            Text("Cancel")
                        }

                        TextButton(
                            onClick = {
                                onConfirm("${timePickerState.hour}:${timePickerState.minute}")
                                state.dismissDialog()
                            }
                        ) {
                            Text("Done")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PriorityDropdownMenu(modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(Priority.LOW.name.capitalize()) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            value = selected,
            onValueChange = { selected = it },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .then(modifier)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            Priority.entries.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.name.capitalize()) },
                    onClick = {
                        selected = it.name.capitalize()
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = it.icon,
                            tint = it.color,
                            contentDescription = null
                        )
                    }
                )
            }.also {
                DropdownMenuItem(
                    text = { Text(text = "Create a new one") },
                    onClick = {
                        // handle create new priority title here
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = AppIcons.Create,
                            contentDescription = stringResource(
                                id = R.string.create
                            )
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskGroupDropdownMenu(modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember {
        mutableStateOf(
            FakeTaskGroupData.getAllFakeTaskGroups().first().title
        )
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            value = selected,
            onValueChange = { selected = it },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier
                .menuAnchor()
                .then(modifier)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.exposedDropdownSize()
        ) {
            FakeTaskGroupData.getAllFakeTaskGroups().forEach {
                DropdownMenuItem(
                    text = { Text(text = it.title.capitalize()) },
                    onClick = {
                        selected = it.title.capitalize()
                        expanded = false
                    }
                )
            }.also {
                DropdownMenuItem(
                    text = { Text(text = "Create a new one") },
                    onClick = {
                        // handle create new priority title here
                        expanded = false
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = AppIcons.Create,
                            contentDescription = stringResource(
                                id = R.string.create
                            )
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewCreateTaskBottomSheetLight() {
    AppTheme {
        CreateTaskDialog(dismissDialog = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCreateTaskBottomSheetDark() {
    AppTheme {
        CreateTaskDialog(dismissDialog = {})
    }
}