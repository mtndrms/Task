package io.minimaltools.task.presentation.feature.home

import androidx.compose.runtime.MutableState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.minimaltools.task.presentation.navigation.TopLevelDestination

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(route = TopLevelDestination.HOME.route, navOptions = navOptions)
}

fun NavGraphBuilder.homeScreen(
    onShowSnackbar: suspend (String, String) -> Boolean,
    createTaskDialogVisibilityState: MutableState<Boolean>
) {
    composable(route = TopLevelDestination.HOME.route) {
        HomeRoute(
            onShowSnackbar = onShowSnackbar,
            createTaskDialogVisibilityState = createTaskDialogVisibilityState
        )
    }
}