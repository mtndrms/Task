package io.minimaltools.task.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import io.minimaltools.task.R
import io.minimaltools.task.presentation.common.AppIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextResId: Int,
    val titleTextResId: Int,
    val route: String
) {
    HOME(
        selectedIcon = AppIcons.Home,
        unselectedIcon = AppIcons.HomeOutlined,
        iconTextResId = R.string.home,
        titleTextResId = R.string.home,
        route = "home"
    ),
    SETTINGS(
        selectedIcon = AppIcons.Settings,
        unselectedIcon = AppIcons.SettingsOutlined,
        iconTextResId = R.string.settings,
        titleTextResId = R.string.settings,
        route = "profile"
    )
}