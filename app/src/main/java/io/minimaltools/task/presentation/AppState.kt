package io.minimaltools.task.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState {
    return remember(navController) {
        AppState(
            navController = navController,
            coroutineScope = coroutineScope,
        )
    }
}

class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
}