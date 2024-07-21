package com.mockingb.circles

import androidx.lifecycle.ViewModel
import kotlin.math.abs
import kotlin.math.sqrt

open class CircleViewModel : ViewModel() {
    open fun checkCircles(
        x1: Double?,
        y1: Double?,
        r1: Double?,
        x2: Double?,
        y2: Double?,
        r2: Double?
    ): String {
        if (x1 == null || y1 == null || r1 == null || x2 == null || y2 == null || r2 == null) {
            return "Input valid numbers"
        }
        val distance: Double = sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))

        return when {
            x1 == x2 && y1 == y2 && r1 == r2 -> "The circles are equal"
            distance > r1 + r2 -> "Circles do not intersect"
            distance == r1 + r2 -> "Circles intersects in one point"
            distance < abs(r1 - r2) -> "One circle is inside the other"
            else -> "Circles intersects in two points"
        }
    }
}