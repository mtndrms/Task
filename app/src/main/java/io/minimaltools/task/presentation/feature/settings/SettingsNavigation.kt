package io.minimaltools.task.presentation.feature.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.minimaltools.task.presentation.navigation.TopLevelDestination

fun NavController.navigateToSettingsScreen(navOptions: NavOptions? = null) {
    this.navigate(route = TopLevelDestination.SETTINGS.route, navOptions = navOptions)
}

fun NavGraphBuilder.settingsScreen() {
    composable(route = TopLevelDestination.SETTINGS.route) {
        SettingsRoute()
    }
}