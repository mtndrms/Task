package io.minimaltools.task.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.minimaltools.task.data.local.entity.group.TaskGroup

@Dao
interface TaskGroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOne(taskGroup: TaskGroup)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(taskGroups: List<TaskGroup>)

    @Query("SELECT * FROM task_group")
    fun getAll(): List<TaskGroup>

    @Query("SELECT * FROM task_group WHERE id=:id")
    fun getOneById(id: Int): TaskGroup

    @Update
    fun updateOne(taskGroup: TaskGroup)

    @Delete
    fun deleteOne(taskGroup: TaskGroup)

    @Query("DELETE FROM task_group WHERE id=:id")
    fun deleteOneByID(id: Int)
}