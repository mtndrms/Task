package io.minimaltools.task.presentation.feature.home.create_task

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import io.minimaltools.task.data.local.entity.group.TaskGroup
import io.minimaltools.task.data.local.entity.task.Task
import io.minimaltools.task.data.local.entity.task.priority.Priority
import io.minimaltools.task.presentation.common.AppIcons
import io.minimaltools.task.presentation.component.DateTimePicker
import io.minimaltools.task.presentation.theme.AppTheme
import io.minimaltools.task.util.DateUtils
import io.minimaltools.task.util.capitalize

@Composable
fun CreateTaskDialog(createTask: (Task) -> Unit, dismissDialog: () -> Unit) {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val date = remember { mutableLongStateOf(0L) }
    val time = remember { mutableStateOf("") }
    val status = remember { mutableStateOf(false) }
    val priority = remember { mutableStateOf(Priority.LOW) }
    val taskGroup = remember { mutableStateOf(FakeTaskGroupData.getTaskGroupForPreview()) }

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
                TopBar(
                    task = Task(
                        name = title.value,
                        description = description.value,
                        dueDate = DateUtils.epochToDateString(date.longValue),
                        dueTime = time.value,
                        priority = priority.value,
                        taskGroup = taskGroup.value.id,
                        status = status.value
                    ),
                    createTask = createTask,
                    dismissDialog = dismissDialog
                )
                Spacer(modifier = Modifier.height(height = 10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Title(title)
                    Description(description)

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.weight(1f)) {
                            SectionTitle(title = stringResource(id = R.string.due_date))
                            DateTimePicker(
                                onCompleted = { selectedDate, selectedTime ->
                                    date.longValue = selectedDate
                                    time.value = selectedTime
                                }
                            ) { visibilityState ->
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
                                            visibilityState.value = true
                                        }
                                ) {
                                    Text(
                                        text = "${
                                            if (date.longValue == 0L) {
                                                DateUtils.getPlaceholderDate()
                                            } else {
                                                DateUtils.epochToDateString(date.longValue)
                                            }
                                        } - ${
                                            time.value.ifEmpty { "00:00" }
                                        }",
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            StatusCheckbox(status)
                        }
                    }
                    Spacer(modifier = Modifier.height(height = 25.dp))

                    PriorityDropdownMenu(priority = priority, modifier = Modifier.fillMaxWidth())
                    TaskGroupDropdownMenu(taskGroup = taskGroup, modifier = Modifier.fillMaxWidth())

                    Spacer(modifier = Modifier.height(height = 25.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(task: Task, createTask: (Task) -> Unit, dismissDialog: () -> Unit) {
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
                    createTask(task)
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
private fun Title(title: MutableState<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SectionTitle(title = stringResource(id = R.string.title))
        TextField(
            value = title.value, onValueChange = { title.value = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 25.dp))
    }
}

@Composable
private fun Description(description: MutableState<String>) {
    SectionTitle(title = stringResource(id = R.string.description))
    TextField(
        value = description.value, onValueChange = { description.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
    )
    Spacer(modifier = Modifier.height(height = 25.dp))
}

@Composable
private fun StatusCheckbox(status: MutableState<Boolean>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        SectionTitle(title = stringResource(id = R.string.status))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = status.value, onCheckedChange = { status.value = it })
            Text(text = "Mark as done")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PriorityDropdownMenu(priority: MutableState<Priority>, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        SectionTitle(title = stringResource(id = R.string.priority))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            TextField(
                value = priority.value.name.capitalize(),
                onValueChange = { },
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
                            priority.value = it
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

        Spacer(modifier = Modifier.height(height = 25.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskGroupDropdownMenu(
    taskGroup: MutableState<TaskGroup>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        SectionTitle(title = stringResource(id = R.string.task_group))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            TextField(
                value = taskGroup.value.title,
                onValueChange = { },
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
                            taskGroup.value = it
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
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewCreateTaskBottomSheetLight() {
    AppTheme {
        CreateTaskDialog(createTask = {}, dismissDialog = {})
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCreateTaskBottomSheetDark() {
    AppTheme {
        CreateTaskDialog(createTask = {}, dismissDialog = {})
    }
}