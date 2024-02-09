package io.minimaltools.task.util

import androidx.compose.runtime.MutableState

fun MutableState<Boolean>.isVisible(): Boolean = this.value

fun MutableState<Boolean>.dismissDialog() {
    this.value = false
}

fun MutableState<Boolean>.showDialog() {
    this.value = true
}
