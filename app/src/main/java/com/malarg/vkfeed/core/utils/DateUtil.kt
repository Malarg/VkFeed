package com.malarg.vkfeed.core.utils

import android.annotation.SuppressLint
import android.content.Context
import com.malarg.vkfeed.R
import com.malarg.vkfeed.core.platform.App
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateUtil {

    @Inject
    lateinit var context: Context

    init {
        App.appComponent.inject(this)
    }

    fun parseDate(dateFormat: String, timeStamp: Long): String {
        val dateFormatSymbols = object : DateFormatSymbols() {
            override fun getMonths(): Array<String> {
                return listOf(
                    context.getString(R.string.january),
                    context.getString(R.string.february),
                    context.getString(R.string.march),
                    context.getString(R.string.april),
                    context.getString(R.string.may),
                    context.getString(R.string.june),
                    context.getString(R.string.july),
                    context.getString(R.string.august),
                    context.getString(R.string.september),
                    context.getString(R.string.october),
                    context.getString(R.string.november),
                    context.getString(R.string.december)
                ).toTypedArray()
            }
        }

        return SimpleDateFormat(dateFormat, dateFormatSymbols).format(timeStamp * 1000)
    }

    fun dateFromTimestamp(timestamp: Long): Date {
        return Date(timestamp * 1000)
    }

    fun formatDate(date: Date, format: String): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
        return simpleDateFormat.format(date)
    }


    fun areDateDaysDifferent(date1: Date, date2: Date): Boolean {
        val date1Formatted = formatDate(date1, "dd.MM.yyyy")
        val date2Formatted = formatDate(date2, "dd.MM.yyyy")

        return date1Formatted != date2Formatted
    }
}