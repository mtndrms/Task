package io.minimaltools.task.data.fake.task

import io.minimaltools.task.data.fake.group.FakeTaskGroupData
import io.minimaltools.task.data.local.entity.task.priority.Priority
import io.minimaltools.task.data.local.entity.task.Task

object FakeTaskData {
    private val task1 = Task(
        id = 0,
        name = "Quantum Leap Experiment",
        dueDate = "2023-02-10",
        dueTime = "00:00",
        priority = Priority.HIGH,
        description = "Conduct a quantum leap experiment to explore the possibilities of time travel. Remember to wear the special socks!",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task2 = Task(
        id = 0,
        name = "Unicorn Wrangling Training",
        dueDate = "2023-03-05",
        dueTime = "01:00",
        priority = Priority.MEDIUM,
        description = "Attend a unicorn wrangling workshop to enhance magical creature handling skills. Bring rainbow lassos and enchanted snacks.",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task3 = Task(
        id = 0,
        name = "Underwater Basket Weaving Tournament",
        dueDate = "2023-04-15",
        dueTime = "04:00",
        priority = Priority.LOW,
        description = "Practice underwater basket weaving for the upcoming international tournament. Don't forget the scuba gear and waterproof wicker.",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task4 = Task(
        id = 0,
        name = "Alien Diplomacy Summit",
        dueDate = "2023-06-20",
        dueTime = "08:00",
        priority = Priority.HIGH,
        description = "Attend the intergalactic diplomacy summit to represent Earth. Brush up on extraterrestrial etiquette and learn to say 'hello' in three alien languages.",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task5 = Task(
        id = 0,
        name = "Extreme Juggling Marathon",
        dueDate = "2023-07-30",
        dueTime = "09:00",
        priority = Priority.MEDIUM,
        description = "Train for the extreme juggling marathon by practicing with flaming torches and chainsaws. Safety first!",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task6 = Task(
        id = 0,
        name = "Bigfoot Expedition",
        dueDate = "2023-09-08",
        dueTime = "20:00",
        priority = Priority.HIGH,
        description = "Embark on a Bigfoot expedition to gather evidence of the elusive creature. Pack a camera, snacks, and a spare pair of binoculars.",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task7 = Task(
        id = 0,
        name = "Time Capsule Preparation",
        dueDate = "2023-10-25",
        dueTime = "22:15",
        priority = Priority.LOW,
        description = "Assemble items for a time capsule burial. Include a Rubik's cube, a mixtape of 80s hits, and a fidget spinner for future generations.",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task8 = Task(
        id = 0,
        name = "Ninja Cat Training",
        dueDate = "2023-12-01",
        dueTime = "16:30",
        priority = Priority.MEDIUM,
        description = "Train the family cat in the ancient art of ninja skills. Obtain a tiny ninja costume for optimal training sessions.",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task9 = Task(
        id = 0,
        name = "Pirate Treasure Hunt",
        dueDate = "2024-01-15",
        dueTime = "22:30",
        priority = Priority.HIGH,
        description = "Plan a pirate treasure hunt with a detailed map and hidden clues. Ensure that the treasure chest contains chocolate doubloons and gummy worms.",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    private val task10 = Task(
        id = 0,
        name = "Robot Dance Competition",
        dueDate = "2024-02-28",
        dueTime = "19:40",
        priority = Priority.LOW,
        description = "Choreograph a dance routine for the upcoming robot dance competition. Program the robot to breakdance and perform the moonwalk.",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup()
    )

    fun getAllFakeTasks(): List<Task> {
        return listOf(task1, task2, task3, task4, task5, task6, task7, task8, task9, task10)
    }

    fun getRandomTask(): Task {
        return getAllFakeTasks().random()
    }

    fun getTaskForPreview(): Task {
        return getAllFakeTasks()[0]
    }
}