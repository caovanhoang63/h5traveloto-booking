package com.example.h5traveloto_booking.ui_shared_components.my_calendar.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.h5traveloto_booking.ui_shared_components.my_calendar.range.RangeConfig
//import io.wojciechosak.calendar.range.RangeConfig
import kotlinx.datetime.LocalDate

/**
 * Internal function to modify the drawing behavior to illustrate date ranges.
 *
 * @param date The date to modify the drawing for.
 * @param selectedDates The list of selected dates.
 * @param config The configuration for drawing the range. Default is null.
 * @return A modified modifier with the drawing behavior adjusted for illustrating date ranges.
 */
internal fun Modifier.drawRange(
    date : LocalDate,
    selectedDates : List<LocalDate>,
    config : RangeConfig? = null,
) = composed {
    if (config == null) return@composed this

    drawBehind {
        with(config) {
            val range =
                if (selectedDates.size == 2) {
                    if (selectedDates.first() >= selectedDates.last()) {
                        Pair(selectedDates.last(), selectedDates.first())
                    } else {
                        Pair(selectedDates.first(), selectedDates.last())
                    }
                } else {
                    null
                }

            if (range != null && date == range.second) {
                rangeIllustrator.drawEnd(this@drawBehind)
            } else if (range != null && date == range.first) {
                rangeIllustrator.drawStart(this@drawBehind)
            } else if (range != null && date in (range.first..range.second)) {
                rangeIllustrator.drawMiddle(this@drawBehind)
            }
        }
    }
}

fun Modifier.RangeStartDate(
    strokeWidth : Int,
    color : Color
) = composed {
    drawBehind {
        val rangeStrokeWidth = strokeWidth * density
        val yDown = size.height
        val yUp = 0f
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = -180f,
            useCenter = false,
            style = Stroke(
                width = rangeStrokeWidth
            )
        )
        drawLine(
            color = color,
            start =  Offset(size.width/2, yDown),
            end =  Offset(size.width, yDown),
            strokeWidth = rangeStrokeWidth
        )
        drawLine(
            color = color,
            Offset(size.width/2, yUp),
            Offset(size.width, yUp),
            rangeStrokeWidth
        )
    }
}

fun Modifier.RangeMidDay (
    strokeWidth : Int,
    color : Color
) = composed {
    drawBehind {
        val rangeStrokeWidth = strokeWidth * density
        val yDown = size.height
        val yUp = 0f
        drawLine(
            color = color,
            start =  Offset(0f, yDown),
            end =  Offset(size.width, yDown),
            strokeWidth = rangeStrokeWidth
        )
        drawLine(
            color = color,
            Offset(0f, yUp),
            Offset(size.width, yUp),
            rangeStrokeWidth
        )
    }
}

fun Modifier.RangeEndDay (
    strokeWidth : Int,
    color : Color
) = composed {
    drawBehind {
        val rangeStrokeWidth = strokeWidth * density
        val yDown = size.height
        val yUp = 0f
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = 180f,
            useCenter = false,
            style = Stroke(
                width = rangeStrokeWidth
            )
        )
        drawLine(
            color = color,
            start =  Offset(0f, yDown),
            end =  Offset(size.width/2, yDown),
            strokeWidth = rangeStrokeWidth
        )
        drawLine(
            color = color,
            Offset(0f, yUp),
            Offset(size.width/2, yUp),
            rangeStrokeWidth
        )
    }
}

fun Modifier.RangeFullDay (
    strokeWidth : Int,
    color : Color
) = composed {
    drawBehind {
        val rangeStrokeWidth = strokeWidth * density
        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(
                width = rangeStrokeWidth
            )
        )
    }
}