package io.minimaltools.task.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.minimaltools.task.data.fake.task.FakeTaskData
import io.minimaltools.task.data.local.entity.task.Task
import io.minimaltools.task.data.local.entity.task.priority.Priority
import io.minimaltools.task.presentation.component.DateRangePicker
import io.minimaltools.task.presentation.feature.home.create_task.CreateTaskDialog
import io.minimaltools.task.presentation.theme.AppTheme
import io.minimaltools.task.util.DateUtils
import io.minimaltools.task.util.capitalize
import io.minimaltools.task.util.dismiss
import io.minimaltools.task.util.isVisible
import io.minimaltools.task.util.show

@Composable
internal fun HomeRoute(
    changeTitle: (String) -> Unit,
    onShowSnackbar: suspend (String, String) -> Boolean,
    viewModel: HomeViewModel = hiltViewModel(),
    createTaskDialogVisibilityState: MutableState<Boolean>,
    floatingActionButtonVisibilityState: MutableState<Boolean>,
    dateRangePickerVisibilityState: MutableState<Boolean>
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        createTaskDialogVisibilityState = createTaskDialogVisibilityState,
        floatingActionButtonVisibilityState = floatingActionButtonVisibilityState,
        dateRangePickerVisibilityState = dateRangePickerVisibilityState,
        onShowSnackbar = onShowSnackbar,
        createTask = viewModel::createTask,
        pinTask = viewModel::pinTask,
        filterByDate = viewModel::filterByDateRange,
        clearUndoState = viewModel::clearUndoState,
        changeTitle = changeTitle
    )
}

@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    createTaskDialogVisibilityState: MutableState<Boolean>,
    floatingActionButtonVisibilityState: MutableState<Boolean>,
    dateRangePickerVisibilityState: MutableState<Boolean>,
    onShowSnackbar: suspend (String, String) -> Boolean,
    createTask: (Task) -> Unit,
    pinTask: () -> Unit,
    filterByDate: (Long, Long) -> Unit,
    clearUndoState: () -> Unit,
    changeTitle: (String) -> Unit
) {
    LaunchedEffect(uiState.shouldDisplayPinnedTaskSnackbar) {
        if (uiState.shouldDisplayPinnedTaskSnackbar) {
            val snackbarResult = onShowSnackbar("Task pinned", "Undo")
            if (snackbarResult) {
                clearUndoState()
            } else {
                clearUndoState()
            }
        }
    }

    if (dateRangePickerVisibilityState.isVisible()) {
        DateRangePicker(
            onCompleted = { startDate, endDate ->
                dateRangePickerVisibilityState.dismiss()
                filterByDate(startDate, endDate)
                changeTitle(
                    "${
                        DateUtils.millisecondsToDateString(startDate)
                    } - ${
                        DateUtils.millisecondsToDateString(endDate)
                    }"
                )
            },
            onCancel = {
                dateRangePickerVisibilityState.dismiss()
            }
        )
    }

    if (createTaskDialogVisibilityState.isVisible()) {
        CreateTaskDialog(
            createTask = { task: Task ->
                createTask(task)
            },
            dismissDialog = {
                createTaskDialogVisibilityState.dismiss()
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(object : NestedScrollConnection {
                    override fun onPreScroll(
                        available: Offset,
                        source: NestedScrollSource
                    ): Offset {
                        if (available.y < -1) {
                            floatingActionButtonVisibilityState.dismiss()
                        }

                        if (available.y > 1) {
                            floatingActionButtonVisibilityState.show()
                        }

                        return Offset.Zero
                    }
                })
        ) {
            items(
                items = uiState.tasks,
                key = { it.id }
            ) { task: Task ->
                Spacer(modifier = Modifier.height(10.dp))
                TaskItem(
                    name = task.name,
                    dueDate = task.dueDate,
                    dueTime = task.dueTime,
                    priority = task.priority,
                    description = task.description,
                    isChecked = task.status,
                    isPinned = false,
                    pinTask = pinTask
                )
            }
        }
    }
}

@Composable
private fun TaskItem(
    name: String,
    dueDate: String,
    dueTime: String,
    priority: Priority,
    description: String,
    isChecked: Boolean,
    isPinned: Boolean,
    pinTask: () -> Unit
) {
    // this two states gonna be removed when the local database is implemented
    var pinnedState by remember { mutableStateOf(isPinned) }
    var checkedState by remember { mutableStateOf(isChecked) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 8.dp, end = 16.dp)
            .clip(shape = RoundedCornerShape(bottomEnd = 8.dp))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedState,
                onCheckedChange = { checkedState = checkedState.not() }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomStart = 8.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.surface),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 8.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .weight(1f, fill = true)
                        .padding(vertical = 16.dp, horizontal = 12.dp),
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W500,
                            fontSize = 16.sp,
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                        ),
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = priority.name.capitalize(),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W300,
                                fontSize = 13.sp
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                        Text(
                            text = "|",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W500,
                                fontSize = 13.sp
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(horizontal = 3.dp)
                        )
                        Text(
                            text = "$dueDate at $dueTime",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.W300,
                                fontSize = 13.sp
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.End,
                        )
                    }
                }
            }
        }
        TrianglePriorityIndicator(
            color = priority.color,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Composable
private fun TrianglePriorityIndicator(color: Color, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier
        .size(15.dp)
        .drawWithCache {
            val path = Path()
            path.moveTo(size.width, 0f)
            path.lineTo(size.width, size.height)
            path.lineTo(0f, size.height)
            path.close()
            onDrawBehind {
                drawPath(path, color)
            }
        }
        .then(modifier))
}

@PreviewLightDark
@PreviewFontScale
@Composable
private fun PreviewHomeScreen() {
    AppTheme {
        HomeScreen(
            uiState = HomeUiState(
                tasks = FakeTaskData.getAllFakeTasks(),
                shouldDisplayPinnedTaskSnackbar = false
            ),
            onShowSnackbar = { _, _ -> true },
            createTask = {},
            pinTask = { /*TODO*/ },
            clearUndoState = { /*TODO*/ },
            filterByDate = { s, s2 -> },
            createTaskDialogVisibilityState = remember { mutableStateOf(false) },
            floatingActionButtonVisibilityState = remember { mutableStateOf(false) },
            dateRangePickerVisibilityState = remember { mutableStateOf(false) },
            changeTitle = {}
        )
    }
}


@PreviewLightDark
@PreviewFontScale
@Composable
private fun PreviewTaskItem() {
    AppTheme {
        TaskItem(
            name = FakeTaskData.getTaskForPreview().name,
            dueDate = FakeTaskData.getTaskForPreview().dueDate,
            dueTime = FakeTaskData.getTaskForPreview().dueTime,
            priority = FakeTaskData.getTaskForPreview().priority,
            description = FakeTaskData.getTaskForPreview().description,
            isChecked = false,
            isPinned = false,
            pinTask = {}
        )
    }
}