package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd:MM:yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    //0с - 1с "только что"
    //1с - 45с "несколько секунд назад"
    //45с - 75с "минуту назад"
    //75с - 45мин "N минут назад"
    //45мин - 75мин "час назад"
    //75мин 22ч "N часов назад"
    //22ч - 26ч "день назад"
    //26ч - 360д "N дней назад"
    //>360д "более года назад"

    val diffTime = date.time - this.time

    return when (Math.abs(diffTime).toInt()) {

        else -> "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}