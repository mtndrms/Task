package io.minimaltools.task.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.minimaltools.task.R
import io.minimaltools.task.data.fake.group.FakeTaskGroupData.getAllFakeTaskGroups
import io.minimaltools.task.data.fake.group.TaskGroup
import io.minimaltools.task.presentation.common.AppIcons
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskApp(appState: AppState = rememberAppState()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var taskGroups by remember { mutableStateOf(emptyList<TaskGroup>()) }
    val snackbarHostState = remember { SnackbarHostState() }
    val bottomSheetState = rememberModalBottomSheetState()
    var bottomSheetVisibilityState by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        taskGroups = getAllFakeTaskGroups()
    }

    fun hideBottomSheet() {
        appState.coroutineScope.launch {
            bottomSheetState.hide()
            bottomSheetVisibilityState = bottomSheetState.isVisible
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                ) {
                    Text(stringResource(id = R.string.app_name), modifier = Modifier.padding(16.dp))
                    Divider()
                    Text("Groups", modifier = Modifier.padding(16.dp))
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        taskGroups.forEach { taskGroup: TaskGroup ->
                            NavigationDrawerItem(
                                label = { Text(text = taskGroup.title) },
                                selected = false,
                                badge = { Text(text = "${taskGroup.done}/${taskGroup.total}") },
                                onClick = { }
                            )
                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Today") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                appState.coroutineScope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                            content = {
                                Icon(
                                    imageVector = AppIcons.Menu,
                                    contentDescription = "menu"
                                )
                            }
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                            },
                            content = {
                                Icon(
                                    imageVector = AppIcons.More,
                                    contentDescription = "more"
                                )
                            }
                        )
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        appState.coroutineScope.launch {
                            bottomSheetState.expand()
                            bottomSheetVisibilityState = bottomSheetState.isVisible
                        }
                    },
                    content = {
                        Icon(imageVector = AppIcons.Add, contentDescription = "create new task")
                    }
                )
            },
            snackbarHost = { SnackbarHost(snackbarHostState) },
            modifier = Modifier.fillMaxSize()
        ) { paddingValues: PaddingValues ->
            AppNavHost(
                appState = appState,
                onShowSnackbar = { message, action ->
                    snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = action,
                        duration = SnackbarDuration.Short
                    ) == SnackbarResult.ActionPerformed
                },
                isBottomSheetVisible = bottomSheetVisibilityState,
                hideBottomSheet = ::hideBottomSheet,
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}