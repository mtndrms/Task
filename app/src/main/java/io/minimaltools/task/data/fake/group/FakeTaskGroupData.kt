package io.minimaltools.task.data.fake.group

object FakeTaskGroupData {
    private val group1 = TaskGroup(
        title = "My Tasks",
        total = 10,
        done = 0
    )
    private val group2 = TaskGroup(
        title = "Shopping List",
        total = 7,
        done = 4
    )
    private val group3 = TaskGroup(
        title = "Renovation",
        total = 8,
        done = 2
    )
    private val group4 = TaskGroup(
        title = "New Year",
        total = 10,
        done = 10
    )

    fun getAllFakeTaskGroups(): List<TaskGroup> {
        return listOf(group1, group2, group3, group4)
    }
}