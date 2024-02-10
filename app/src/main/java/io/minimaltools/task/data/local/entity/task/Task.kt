package io.minimaltools.task.data.local.entity.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.minimaltools.task.data.local.entity.group.TaskGroup
import io.minimaltools.task.data.local.entity.task.priority.Priority

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "due_date") val dueDate: String,
    @ColumnInfo(name = "due_time") val dueTime: String,
    @ColumnInfo(name = "priority") val priority: Priority,
    @ColumnInfo(name = "task_group") val taskGroup: TaskGroup,
    @ColumnInfo(name = "status") val status: Boolean
)