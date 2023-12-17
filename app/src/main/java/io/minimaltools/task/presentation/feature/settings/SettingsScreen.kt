package io.minimaltools.task.presentation.feature.settings

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SettingsRoute(viewModel: SettingsViewModel = hiltViewModel()) {
    SettingsScreen()
}

@Composable
private fun SettingsScreen() {
    // screen content
}