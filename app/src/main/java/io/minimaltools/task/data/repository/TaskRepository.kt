package io.minimaltools.task.data.repository

import io.minimaltools.task.data.local.LocalDataSource
import io.minimaltools.task.data.local.entity.group.TaskGroup
import io.minimaltools.task.data.local.entity.task.Priority
import io.minimaltools.task.data.local.entity.task.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(private val localDataSource: LocalDataSource) {
    suspend fun insertOne(task: Task) =
        localDataSource.insertOneTask(task)

    suspend fun insertAll(tasks: List<Task>) =
        localDataSource.insertAllTasks(tasks)

    suspend fun getAll() =
        localDataSource.getAllTasks()

    suspend fun getOneByID(id: Int) =
        localDataSource.getOneTaskByID(id)

    suspend fun getAllByTaskGroup(taskGroup: TaskGroup) =
        localDataSource.getAllTasksByTaskGroup(taskGroup)

    suspend fun getAllByStatus(status: Boolean) =
        localDataSource.getAllTasksByStatus(status)

    suspend fun getAllByPriority(priority: Priority) =
        localDataSource.getAllTasksByPriority(priority)

    suspend fun updateOne(task: Task) =
        localDataSource.updateOneTask(task)

    suspend fun updateOneStatusByID(id: Int, status: Boolean) =
        localDataSource.updateOneTaskStatusByID(id, status)

    suspend fun updateMultipleStatus(ids: List<Int>, status: Boolean) =
        localDataSource.updateMultipleTaskStatus(ids, status)

    suspend fun deleteOne(task: Task) =
        localDataSource.deleteOneTask(task)

    suspend fun deleteOneByID(id: Int) =
        localDataSource.deleteOneTaskByID(id)

    suspend fun deleteAllByID(ids: List<Int>) =
        localDataSource.deleteAllTasksByID(ids)

    suspend fun deleteAllByTaskGroup(taskGroup: TaskGroup) =
        localDataSource.deleteAllTasksByTaskGroup(taskGroup)

    suspend fun deleteAllByStatus(status: Boolean) =
        localDataSource.deleteAllTasksByStatus(status)
}