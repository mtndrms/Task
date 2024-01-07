package io.minimaltools.task.presentation.feature.home

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.minimaltools.task.data.fake.task.FakeTaskData.previewTask
import io.minimaltools.task.data.local.entity.task.Priority
import io.minimaltools.task.data.local.entity.task.Task
import io.minimaltools.task.presentation.common.AppIcons
import io.minimaltools.task.presentation.theme.AppTheme

@Composable
internal fun HomeRoute(
    onShowSnackbar: suspend (String, String) -> Boolean,
    viewModel: HomeViewModel = hiltViewModel(),
    isBottomSheetVisible: Boolean,
    hideBottomSheet: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        onShowSnackbar = onShowSnackbar,
        pinTask = viewModel::pinTask,
        clearUndoState = viewModel::clearUndoState,
        isBottomSheetVisible = isBottomSheetVisible,
        hideBottomSheet = hideBottomSheet
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onShowSnackbar: suspend (String, String) -> Boolean,
    pinTask: () -> Unit,
    clearUndoState: () -> Unit,
    isBottomSheetVisible: Boolean,
    hideBottomSheet: () -> Unit
) {
    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = hideBottomSheet
        ) {

        }
    }

    LaunchedEffect(uiState.shouldDisplayPinnedTaskSnackbar) {
        if (uiState.shouldDisplayPinnedTaskSnackbar) {
            val snackbarResult = onShowSnackbar("Task pinned", "Undo")
            if (snackbarResult) {
                Log.i("HomeScreen", "Perform undo operation here!")
                clearUndoState()
            } else {
                clearUndoState()
            }
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = uiState.tasks) { task: Task ->
            Spacer(modifier = Modifier.height(5.dp))
            TaskItem(
                name = task.name,
                dueDate = task.dueDate,
                priority = task.priority,
                description = task.description,
                isChecked = false,
                isMarked = false,
                pinTask = pinTask
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Composable
private fun TaskItem(
    name: String,
    dueDate: String,
    priority: Priority,
    description: String,
    isChecked: Boolean,
    isMarked: Boolean,
    pinTask: () -> Unit
) {
    var markedState by remember { mutableStateOf(isMarked) }
    var checkedState by remember { mutableStateOf(isChecked) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.background)
            .padding(start = 8.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = { checkedState = checkedState.not() }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 8.dp)
                .clip(shape = RoundedCornerShape(size = 8.dp))
                .background(MaterialTheme.colorScheme.surface),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
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
            }
            IconButton(
                onClick = {
                    markedState = markedState.not()
                    pinTask()
                }) {
                Icon(
                    painter = painterResource(
                        id = if (markedState) AppIcons.Star else AppIcons.StarOutline
                    ),
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "favorite"
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewTaskItemNewLight() {
    AppTheme {
        TaskItem(
            name = previewTask.name,
            dueDate = previewTask.dueDate,
            priority = previewTask.priority,
            description = previewTask.description,
            isChecked = false,
            isMarked = false,
            pinTask = {}
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewTaskItemNewDark() {
    AppTheme {
        TaskItem(
            name = previewTask.name,
            dueDate = previewTask.dueDate,
            priority = previewTask.priority,
            description = previewTask.description,
            isChecked = false,
            isMarked = false,
            pinTask = {}
        )
    }
}