package io.minimaltools.task.data.fake.task

import io.minimaltools.task.data.local.entity.task.Priority
import io.minimaltools.task.data.local.entity.task.Task

object FakeTaskData {
    val previewTask = Task(
        "Quantum Leap Experiment",
        "2023-02-10",
        Priority.HIGH,
        "Conduct a quantum leap experiment to explore the possibilities of time travel. Remember to wear the special socks!"
    )

    private val task1 = Task(
        "Quantum Leap Experiment",
        "2023-02-10",
        Priority.HIGH,
        "Conduct a quantum leap experiment to explore the possibilities of time travel. Remember to wear the special socks!"
    )

    private val task2 = Task(
        "Unicorn Wrangling Training",
        "2023-03-05",
        Priority.MEDIUM,
        "Attend a unicorn wrangling workshop to enhance magical creature handling skills. Bring rainbow lassos and enchanted snacks."
    )

    private val task3 = Task(
        "Underwater Basket Weaving Tournament",
        "2023-04-15",
        Priority.LOW,
        "Practice underwater basket weaving for the upcoming international tournament. Don't forget the scuba gear and waterproof wicker."
    )

    private val task4 = Task(
        "Alien Diplomacy Summit",
        "2023-06-20",
        Priority.HIGH,
        "Attend the intergalactic diplomacy summit to represent Earth. Brush up on extraterrestrial etiquette and learn to say 'hello' in three alien languages."
    )

    private val task5 = Task(
        "Extreme Juggling Marathon",
        "2023-07-30",
        Priority.MEDIUM,
        "Train for the extreme juggling marathon by practicing with flaming torches and chainsaws. Safety first!"
    )

    private val task6 = Task(
        "Bigfoot Expedition",
        "2023-09-08",
        Priority.HIGH,
        "Embark on a Bigfoot expedition to gather evidence of the elusive creature. Pack a camera, snacks, and a spare pair of binoculars."
    )

    private val task7 = Task(
        "Time Capsule Preparation",
        "2023-10-25",
        Priority.LOW,
        "Assemble items for a time capsule burial. Include a Rubik's cube, a mixtape of 80s hits, and a fidget spinner for future generations."
    )

    private val task8 = Task(
        "Ninja Cat Training",
        "2023-12-01",
        Priority.MEDIUM,
        "Train the family cat in the ancient art of ninja skills. Obtain a tiny ninja costume for optimal training sessions."
    )

    private val task9 = Task(
        "Pirate Treasure Hunt",
        "2024-01-15",
        Priority.HIGH,
        "Plan a pirate treasure hunt with a detailed map and hidden clues. Ensure that the treasure chest contains chocolate doubloons and gummy worms."
    )

    private val task10 = Task(
        "Robot Dance Competition",
        "2024-02-28",
        Priority.LOW,
        "Choreograph a dance routine for the upcoming robot dance competition. Program the robot to breakdance and perform the moonwalk."
    )

    fun getAllFakeTasks(): List<Task> {
        return listOf(task1, task2, task3, task4, task5, task6, task7, task8, task9, task10)
    }
}