@file:JvmName("NumbersUtils")

package com.aboshekh.androidutils

import android.annotation.SuppressLint

/**
 * Formats a duration (in milliseconds) into a clock-like time string.
 *
 * Output format depends on the total duration:
 * - `MM:SS` for durations under 1 hour
 * - `DD:HH:MM:SS` for durations with hours or days
 *
 * @receiver Duration in milliseconds
 * @return Formatted time string
 */
@SuppressLint("DefaultLocale")
fun Long.formatToTime(): String {
    val days = this / 86400000
    val hours = (this % 86400000) / 3600000
    val minutes = (this % 3600000) / 60000
    val seconds = (this % 60000) / 1000

    return if (days >= 1)
        String.format("%02d:%02d:%02d:%02d:", days, hours, minutes, seconds)
    else if (hours >= 1)
        String.format("%02d:%02d:%02d:%02d", days, hours, minutes, seconds)
    else
        String.format("%02d:%02d", minutes, seconds)
}