package io.minimaltools.task.data.local.type_converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.minimaltools.task.data.local.entity.group.TaskGroup

class TaskGroupTypeConverter {
    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromTaskGroup(taskGroup: TaskGroup): String {
        val adapter: JsonAdapter<TaskGroup> = moshi.adapter(TaskGroup::class.java)
        return adapter.toJson(taskGroup)
    }

    @TypeConverter
    fun toTaskGroup(json: String): TaskGroup? {
        val type = Types.newParameterizedType(TaskGroup::class.java)
        val adapter: JsonAdapter<TaskGroup> = moshi.adapter(type)
        return adapter.fromJson(json)
    }
}