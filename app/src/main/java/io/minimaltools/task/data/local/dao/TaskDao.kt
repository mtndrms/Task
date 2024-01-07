package io.minimaltools.task.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.minimaltools.task.data.local.entity.group.TaskGroup
import io.minimaltools.task.data.local.entity.task.Priority
import io.minimaltools.task.data.local.entity.task.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(tasks: List<Task>)

    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Query("SELECT * FROM task WHERE id=:id")
    fun getOneByID(id: Int): Task

    @Query("SELECT * FROM task WHERE task_group=:taskGroup")
    fun getAllByTaskGroup(taskGroup: TaskGroup): List<Task>

    @Query("SELECT * FROM task WHERE status=:status")
    fun getAllByStatus(status: Boolean): List<Task>

    @Query("SELECT * FROM task WHERE priority=:priority")
    fun getAllByPriority(priority: Priority): List<Task>

    @Update
    fun updateOne(task: Task)

    @Query("UPDATE task SET status=:status WHERE id=:id")
    fun updateOneStatus(id: Int, status: Boolean)

    @Query("UPDATE task SET status=:status WHERE id IN (:ids)")
    fun updateMultipleStatus(ids: List<Int>, status: Boolean)

    @Delete
    fun deleteOne(task: Task)

    @Query("DELETE FROM task WHERE id=:id")
    fun deleteOneByID(id: Int)

    @Query("DELETE FROM task WHERE id IN (:ids)")
    fun deleteAllByID(ids: List<Int>)

    @Query("DELETE FROM task WHERE task_group=:taskGroup")
    fun deleteAllByTaskGroup(taskGroup: TaskGroup)

    @Query("DELETE FROM task WHERE status=:status")
    fun deleteAllByStatus(status: Boolean)
}