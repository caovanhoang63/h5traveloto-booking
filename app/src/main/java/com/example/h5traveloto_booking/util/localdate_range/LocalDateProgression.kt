package com.example.h5traveloto_booking.util.localdate_range

import kotlinx.datetime.LocalDate

class LocalDateProgression (
    override val start: LocalDate,
    override val endInclusive: LocalDate
) : ClosedRange<LocalDate>, Iterable<LocalDate> {
    override fun iterator(): Iterator<LocalDate> = LocalDateIterator(start, endInclusive)
}

// operator fun LocalDate.rangeTo(other: LocalDate) = LocalDateProgression(this, other)