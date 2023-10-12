package com.taufik.weatherx.utils

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.taufik.weatherx.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Objects


fun ImageView.loadImage(
    context: Context,
    url: String?
) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.outline_cloud)
        .into(this)
}

fun epochToTime(epochTime: Long): String? {
    val updateDate = Date(epochTime * 1000L)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(updateDate)
}

fun epochToDate(epochDate: Long): String {
    val updateDate = Date(epochDate * 1000L)
    val format = SimpleDateFormat("dd/MM", Locale.getDefault())
    return format.format(updateDate)
}

fun getDayName(date: String?): String {
    val df = SimpleDateFormat("dd/MM", Locale.getDefault())
    var readDate: Date? = null
    try {
        readDate = date?.let { df.parse(it) }
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    val c: Calendar = Calendar.getInstance()
    c.time = Objects.requireNonNull(readDate)
    val dayOfWeek: Int = c.get(Calendar.DAY_OF_WEEK)
    val days = arrayOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )
    return days[dayOfWeek - 1]
}

fun showError(tag: String, message: String?) {
    Log.e(tag, "error message: $message")
}