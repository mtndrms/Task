package io.minimaltools.task.util

import androidx.compose.runtime.MutableState

fun MutableState<Boolean>.isVisible(): Boolean = this.value

fun MutableState<Boolean>.dismiss() {
    this.value = false
}

fun MutableState<Boolean>.show() {
    this.value = true
}
