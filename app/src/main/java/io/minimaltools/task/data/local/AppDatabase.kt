package io.minimaltools.task.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.minimaltools.task.data.local.dao.TaskDao
import io.minimaltools.task.data.local.dao.TaskGroupDao
import io.minimaltools.task.data.local.entity.group.TaskGroup
import io.minimaltools.task.data.local.entity.task.Task

@Database(entities = [Task::class, TaskGroup::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun taskGroupDao(): TaskGroupDao
}