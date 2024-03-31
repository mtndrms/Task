package io.minimaltools.task.data.fake.group

import io.minimaltools.task.data.fake.task.FakeTaskData
import io.minimaltools.task.data.local.entity.group.TaskGroup
import io.minimaltools.task.data.local.entity.task.Task

object FakeTaskGroupData {
    private val group1 = TaskGroup(
        id = 0,
        title = "My Tasks",
        description = "My Tasks description",
        total = 10,
        done = 0
    )
    private val group2 = TaskGroup(
        id = 1,
        title = "Shopping List",
        description = "Shopping List description",
        total = 7,
        done = 4
    )
    private val group3 = TaskGroup(
        id = 2,
        title = "Renovation",
        description = "Renovation description",
        total = 8,
        done = 2
    )
    private val group4 = TaskGroup(
        id = 3,
        title = "New Year",
        description = "New Year description",
        total = 10,
        done = 10
    )

    fun getAllFakeTaskGroupAndItsTasks(): HashMap<TaskGroup, List<Task>> {
        val data: HashMap<TaskGroup, List<Task>> = hashMapOf()
        val tasks = FakeTaskData.getAllFakeTasks()

        getAllFakeTaskGroups().forEach { taskGroup ->
            val filtered = tasks.filter { it.id == taskGroup.id }
            data[taskGroup] = filtered
        }.also {
            return data
        }
    }

    fun getAllFakeTaskGroups(): List<TaskGroup> {
        return listOf(group1, group2, group3, group4)
    }

    fun getRandomTaskGroup(): TaskGroup {
        return getAllFakeTaskGroups().random()
    }

    fun getTaskGroupForPreview(): TaskGroup {
        return getAllFakeTaskGroups()[0]
    }
}