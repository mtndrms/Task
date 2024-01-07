package io.minimaltools.task.data.local

import io.minimaltools.task.data.local.entity.group.TaskGroup
import io.minimaltools.task.data.local.entity.task.Priority
import io.minimaltools.task.data.local.entity.task.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val database: AppDatabase) {
    suspend fun insertOneTask(task: Task) =
        withContext(Dispatchers.IO) {
            database.taskDao().insertOne(task)
        }

    suspend fun insertAllTasks(tasks: List<Task>) =
        withContext(Dispatchers.IO) {
            database.taskDao().insertAll(tasks)
        }

    suspend fun getAllTasks() =
        withContext(Dispatchers.IO) {
            database.taskDao().getAll()
        }

    suspend fun getOneTaskByID(id: Int) =
        withContext(Dispatchers.IO) {
            database.taskDao().getOneByID(id)
        }

    suspend fun getAllTasksByTaskGroup(taskGroup: TaskGroup) =
        withContext(Dispatchers.IO) {
            database.taskDao().getAllByTaskGroup(taskGroup)
        }

    suspend fun getAllTasksByStatus(status: Boolean) =
        withContext(Dispatchers.IO) {
            database.taskDao().getAllByStatus(status)
        }

    suspend fun getAllTasksByPriority(priority: Priority) =
        withContext(Dispatchers.IO) {
            database.taskDao().getAllByPriority(priority)
        }

    suspend fun updateOneTask(task: Task) =
        withContext(Dispatchers.IO) {
            database.taskDao().updateOne(task)
        }

    suspend fun updateOneTaskStatusByID(id: Int, status: Boolean) =
        withContext(Dispatchers.IO) {
            database.taskDao().updateOneStatus(id, status)
        }

    suspend fun updateMultipleTaskStatus(ids: List<Int>, status: Boolean) =
        withContext(Dispatchers.IO) {
            database.taskDao().updateMultipleStatus(ids, status)
        }

    suspend fun deleteOneTask(task: Task) =
        withContext(Dispatchers.IO) {
            database.taskDao().deleteOne(task)
        }

    suspend fun deleteOneTaskByID(id: Int) =
        withContext(Dispatchers.IO) {
            database.taskDao().deleteOneByID(id)
        }

    suspend fun deleteAllTasksByID(ids: List<Int>) =
        withContext(Dispatchers.IO) {
            database.taskDao().deleteAllByID(ids)
        }

    suspend fun deleteAllTasksByTaskGroup(taskGroup: TaskGroup) =
        withContext(Dispatchers.IO) {
            database.taskDao().deleteAllByTaskGroup(taskGroup)
        }

    suspend fun deleteAllTasksByStatus(status: Boolean) =
        withContext(Dispatchers.IO) {
            database.taskDao().deleteAllByStatus(status)
        }

    suspend fun insertOneTaskGroup(taskGroup: TaskGroup) =
        withContext(Dispatchers.IO) {
            database.taskGroupDao().insertOne(taskGroup)
        }

    suspend fun insertAllTaskGroups(taskGroups: List<TaskGroup>) =
        withContext(Dispatchers.IO) {
            database.taskGroupDao().insertAll(taskGroups)
        }

    suspend fun getAllTaskGroups() =
        withContext(Dispatchers.IO) {
            database.taskGroupDao().getAll()
        }

    suspend fun getOneTaskGroupByID(id: Int) =
        withContext(Dispatchers.IO) {
            database.taskGroupDao().getOneById(id)
        }

    suspend fun updateOneTaskGroup(taskGroup: TaskGroup) =
        withContext(Dispatchers.IO) {
            database.taskGroupDao().updateOne(taskGroup)
        }

    suspend fun deleteOneTaskGroup(taskGroup: TaskGroup) =
        withContext(Dispatchers.IO) {
            database.taskGroupDao().deleteOne(taskGroup)
        }

    suspend fun deleteOneTaskGroupByID(id: Int) =
        withContext(Dispatchers.IO) {
            database.taskGroupDao().deleteOneByID(id)
        }
}