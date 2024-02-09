package io.minimaltools.task.data.local.entity.task.priority

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import io.minimaltools.task.presentation.common.AppIcons

enum class Priority(val icon: ImageVector,val color: Color) {
    LOW(
        icon = AppIcons.LOW,
        color = Color.Blue
    ),
    MEDIUM(
        icon = AppIcons.MEDIUM,
        color = Color.Yellow
    ),
    HIGH(
        icon = AppIcons.HIGH,
        color = Color.Red
    )
}