package io.minimaltools.task.data.local.entity.group

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "task_group")
@JsonClass(generateAdapter = true)
data class TaskGroup(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "total") val total: Int,
    @ColumnInfo(name = "done") val done: Int
)
