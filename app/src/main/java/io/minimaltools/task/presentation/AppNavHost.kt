package io.minimaltools.task.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import io.minimaltools.task.presentation.feature.home.homeScreen
import io.minimaltools.task.presentation.feature.settings.settingsScreen
import io.minimaltools.task.presentation.navigation.TopLevelDestination

@Composable
fun AppNavHost(
    appState: AppState,
    onShowSnackbar: suspend (String, String) -> Boolean,
    modifier: Modifier,
    isBottomSheetVisible: Boolean,
    hideBottomSheet: () -> Unit
) {
    NavHost(
        navController = appState.navController,
        startDestination = TopLevelDestination.HOME.route,
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
    ) {
        homeScreen(
            onShowSnackbar = onShowSnackbar,
            isBottomSheetVisible = isBottomSheetVisible,
            hideBottomSheet = hideBottomSheet
        )
        settingsScreen()
    }
}