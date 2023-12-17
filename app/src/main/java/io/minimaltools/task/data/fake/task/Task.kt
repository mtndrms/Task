package io.minimaltools.task.data.fake.task

data class Task(
    val name: String,
    val dueDate: String,
    val priority: Priority,
    val description: String
)