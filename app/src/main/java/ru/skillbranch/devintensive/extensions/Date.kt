package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
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
    val diffTime = date.time - this.time

    return when (val absDiffTime = abs(diffTime)) {
        in 0..1 * SECOND -> "только что"
        in 1 * SECOND..45 * SECOND -> if (diffTime < 0) "через несколько секунд" else "несколько секунд назад"
        in 45 * SECOND..75 * SECOND -> if (diffTime < 0) "через минуту" else "минуту назад"
        in 75 * SECOND..45 * MINUTE -> if (diffTime < 0) "через ${TimeUnits.MINUTE.plural((absDiffTime / MINUTE).toInt())}" else "${TimeUnits.MINUTE.plural((absDiffTime / MINUTE).toInt())} назад"
        in 45 * MINUTE..75 * MINUTE -> if (diffTime < 0) "через час" else "час назад"
        in 75 * MINUTE..22 * HOUR -> if (diffTime < 0) "через ${TimeUnits.HOUR.plural((absDiffTime / HOUR).toInt())}" else "${TimeUnits.HOUR.plural((absDiffTime / HOUR).toInt())} назад"
        in 22 * HOUR..26 * HOUR -> if (diffTime < 0) "через день" else "день назад"
        in 26 * HOUR..360 * DAY -> if (diffTime < 0) "через ${TimeUnits.DAY.plural((absDiffTime / DAY).toInt())}" else "${TimeUnits.DAY.plural((absDiffTime / DAY).toInt())} назад"
        else -> if (diffTime < 0) "более чем через год" else "более года назад"
    }
}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String {
            return when (value % 100) {
                in 10..19 -> "$value секунд"
                else -> {
                    when (value % 10) {
                        0, in 5..9 -> "$value секунд"
                        in 2..4 -> "$value секунды"
                        else -> "$value секунду"
                    }
                }
            }
        }
    },
    MINUTE {
        override fun plural(value: Int): String {
            return when (value % 100) {
                in 10..19 -> "$value минут"
                else -> {
                    when (value % 10) {
                        0, in 5..9 -> "$value минут"
                        in 2..4 -> "$value минуты"
                        else -> "$value минуту"
                    }
                }
            }
        }
    },
    HOUR {
        override fun plural(value: Int): String {
            return when (value % 100) {
                in 10..19 -> "$value часов"
                else -> {
                    when (value % 10) {
                        0, in 5..9 -> "$value часов"
                        in 2..4 -> "$value часа"
                        else -> "$value час"
                    }
                }
            }
        }
    },
    DAY {
        override fun plural(value: Int): String {
            return when (value % 100) {
                in 10..19 -> "$value дней"
                else -> when (value % 10) {
                    0, in 5..9 -> "$value дней"
                    in 2..4 -> "$value дня"
                    else -> "$value день"
                }
            }
        }
    };

    abstract fun plural(value: Int): String
}