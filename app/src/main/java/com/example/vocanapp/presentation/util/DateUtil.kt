package com.example.vocanapp.presentation.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Instant.formatReviewDate(): String {

    val zone = ZoneId.systemDefault()
    val dateTime = atZone(zone)

    val formatter = DateTimeFormatter.ofPattern(
        "dd MMM, HH:mm",
        Locale.getDefault()
    )

    return dateTime.format(formatter)
}