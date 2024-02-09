package io.minimaltools.task.util

import java.text.DateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {
    fun millisecondsToDateString(milliseconds: Long): String {
        val date = Date(milliseconds)
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
        return formatter.format(date)
    }

    fun millisecondsToDateTimeString(milliseconds: Long): String {
        val date = Date(milliseconds)
        val formatter =
            DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT,
                Locale.getDefault()
            )

        return formatter.format(date)
    }

    /**
     * @return the date of 1/1/2024 but localized
     */
    fun getPlaceholderDate(): String {
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = 0

        return formatter.format(calendar.time)
    }

    /**
     * @return today's date but localized
     */
    fun getDateNow(): String {
        val current = System.currentTimeMillis()
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
        val date = Date(current)

        return formatter.format(date)
    }
}
