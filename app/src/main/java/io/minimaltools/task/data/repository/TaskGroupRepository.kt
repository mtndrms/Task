package io.minimaltools.task.data.repository

import io.minimaltools.task.data.local.LocalDataSource
import io.minimaltools.task.data.local.entity.group.TaskGroup
import javax.inject.Inject

class TaskGroupRepository @Inject constructor(private val localDataSource: LocalDataSource) {
    suspend fun insertOne(taskGroup: TaskGroup) =
        localDataSource.insertOneTaskGroup(taskGroup)

    suspend fun insertAll(taskGroups: List<TaskGroup>) =
        localDataSource.insertAllTaskGroups(taskGroups)

    suspend fun getAll() =
        localDataSource.getAllTaskGroups()

    suspend fun getOneByID(id: Int) =
        localDataSource.getOneTaskGroupByID(id)

    suspend fun updateOne(taskGroup: TaskGroup) =
        localDataSource.updateOneTaskGroup(taskGroup)

    suspend fun deleteOne(taskGroup: TaskGroup) =
        localDataSource.deleteOneTaskGroup(taskGroup)

    suspend fun deleteOneByID(id: Int) =
        localDataSource.deleteOneTaskGroupByID(id)
}