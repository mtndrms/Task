package io.minimaltools.task.data.fake.group

import io.minimaltools.task.data.local.entity.group.TaskGroup

object FakeTaskGroupData {
    private val group1 = TaskGroup(
        id = 0,
        title = "My Tasks",
        description = "My Tasks description",
        total = 10,
        done = 0
    )
    private val group2 = TaskGroup(
        id = 0,
        title = "Shopping List",
        description = "Shopping List description",
        total = 7,
        done = 4
    )
    private val group3 = TaskGroup(
        id = 0,
        title = "Renovation",
        description = "Renovation description",
        total = 8,
        done = 2
    )
    private val group4 = TaskGroup(
        id = 0,
        title = "New Year",
        description = "New Year description",
        total = 10,
        done = 10
    )

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