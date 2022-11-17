package com.fullstack.mvvm_sample.extensions

import java.util.*

fun addPrefixToTime(int: Int): String {
    return if (int < 10) {
        "0$int"
    } else {
        "" + int
    }
}

object DateFormatters {
    fun stopWatch(createdAt: Long?): String? {
        var timeSpent: String? = null
        createdAt?.let {
            val timePassed = Date().time - it
            val seconds = (timePassed / 1000) % 60
            val minutes = addPrefixToTime ((((timePassed / 1000) / 60) % 60).toInt())
            val hours = (((timePassed / 1000) / 60) / 60) % 60
            val secFormatted = addPrefixToTime(seconds.toInt())
            var toText = ""
            toText = if (hours>0){ "$hours:$minutes:$secFormatted" }else{ "$minutes:$secFormatted" }
            timeSpent = toText
        }
        return timeSpent
    }
}