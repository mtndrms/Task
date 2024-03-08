package io.minimaltools.task.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeParseException
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

    @Throws(ParseException::class)
    fun dateStringToMilliseconds(date: String): Long {
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())

        return try {
            formatter.parse(date)?.time ?: throw ParseException(
                "Could not parsed the date $date. Ignore the offset specified",
                0
            )
        } catch (exception: ParseException) {
            throw ParseException(exception.message, exception.errorOffset)
        }
    }

    /**
     * @return the date of 01/01/1970 but localized
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
