package io.minimaltools.task.presentation.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import io.minimaltools.task.R

object AppIcons {
    val Home: ImageVector = Icons.Default.Home
    val HomeOutlined: ImageVector = Icons.Outlined.Home
    val Settings: ImageVector = Icons.Default.Settings
    val SettingsOutlined: ImageVector = Icons.Outlined.Settings
    val More: ImageVector = Icons.Default.MoreVert
    val Date: ImageVector = Icons.Default.DateRange
    val Menu: ImageVector = Icons.Default.Menu
    val Back: ImageVector = Icons.AutoMirrored.Default.ArrowBack
    val Close: ImageVector = Icons.Default.Close
    val Add: ImageVector = Icons.Default.Add
    val Create: ImageVector = Icons.Default.Create
    val Star: Int = R.drawable.ic_star_filled
    val StarOutline: Int = R.drawable.ic_star_outlined

    // Priorty Icons
    val LOW: ImageVector = Icons.Default.Warning
    val MEDIUM: ImageVector = Icons.Default.Warning
    val HIGH: ImageVector = Icons.Default.Warning
}