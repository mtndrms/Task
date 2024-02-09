package io.minimaltools.task.util

fun String.capitalize(): String {
    return this.lowercase().replaceFirstChar { it.uppercase() }
}

fun String.titleize(): String {
    return ""
}