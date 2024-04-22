package com.example.h5traveloto_booking.util.localdate_range

import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlinx.datetime.DatePeriod

class LocalDateIterator (
    start: LocalDate,
    private val endInclusive: LocalDate
) : Iterator<LocalDate> {
    private var current = start

    override fun hasNext() = current <= endInclusive

    override fun next(): LocalDate {
        val next = current
        current = current.plus(DatePeriod(days = 1))
        return next
    }
}