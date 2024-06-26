package io.minimaltools.task.data.fake.task

import io.minimaltools.task.data.fake.group.FakeTaskGroupData
import io.minimaltools.task.data.local.entity.task.priority.Priority
import io.minimaltools.task.data.local.entity.task.Task

object FakeTaskData {
    private val task1 = Task(
        id = 0,
        name = "Quantum Leap Experiment",
        dueDate = "2/10/2034",
        dueTime = "0:0",
        priority = Priority.HIGH,
        description = "Conduct a quantum leap experiment to explore the possibilities of time travel. Remember to wear the special socks!",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task2 = Task(
        id = 1,
        name = "Unicorn Wrangling Training",
        dueDate = "3/5/2023",
        dueTime = "1:0",
        priority = Priority.MEDIUM,
        description = "Attend a unicorn wrangling workshop to enhance magical creature handling skills. Bring rainbow lassos and enchanted snacks.",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task3 = Task(
        id = 2,
        name = "Underwater Basket Weaving Tournament",
        dueDate = "4/15/2023",
        dueTime = "4:0",
        priority = Priority.LOW,
        description = "Practice underwater basket weaving for the upcoming international tournament. Don't forget the scuba gear and waterproof wicker.",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task4 = Task(
        id = 3,
        name = "Alien Diplomacy Summit",
        dueDate = "6/20/2023",
        dueTime = "8:0",
        priority = Priority.HIGH,
        description = "Attend the intergalactic diplomacy summit to represent Earth. Brush up on extraterrestrial etiquette and learn to say 'hello' in three alien languages.",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task5 = Task(
        id = 4,
        name = "Extreme Juggling Marathon",
        dueDate = "7/30/2023",
        dueTime = "9:0",
        priority = Priority.MEDIUM,
        description = "Train for the extreme juggling marathon by practicing with flaming torches and chainsaws. Safety first!",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task6 = Task(
        id = 5,
        name = "Bigfoot Expedition",
        dueDate = "9/8/2023",
        dueTime = "20:0",
        priority = Priority.HIGH,
        description = "Embark on a Bigfoot expedition to gather evidence of the elusive creature. Pack a camera, snacks, and a spare pair of binoculars.",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task7 = Task(
        id = 6,
        name = "Time Capsule Preparation",
        dueDate = "10/25/2023",
        dueTime = "22:15",
        priority = Priority.LOW,
        description = "Assemble items for a time capsule burial. Include a Rubik's cube, a mixtape of 80s hits, and a fidget spinner for future generations.",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task8 = Task(
        id = 7,
        name = "Ninja Cat Training",
        dueDate = "12/1/2023",
        dueTime = "16:30",
        priority = Priority.MEDIUM,
        description = "Train the family cat in the ancient art of ninja skills. Obtain a tiny ninja costume for optimal training sessions.",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task9 = Task(
        id = 8,
        name = "Pirate Treasure Hunt",
        dueDate = "1/15/2024",
        dueTime = "22:30",
        priority = Priority.HIGH,
        description = "Plan a pirate treasure hunt with a detailed map and hidden clues. Ensure that the treasure chest contains chocolate doubloons and gummy worms.",
        status = true,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
    )

    private val task10 = Task(
        id = 9,
        name = "Robot Dance Competition",
        dueDate = "2/8/2024",
        dueTime = "19:40",
        priority = Priority.LOW,
        description = "Choreograph a dance routine for the upcoming robot dance competition. Program the robot to breakdance and perform the moonwalk.",
        status = false,
        taskGroup = FakeTaskGroupData.getRandomTaskGroup().id
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