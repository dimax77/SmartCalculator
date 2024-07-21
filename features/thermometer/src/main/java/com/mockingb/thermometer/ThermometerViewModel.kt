package com.mockingb.thermometer

import androidx.lifecycle.ViewModel

const val LOW_SUMMER = 22.0
const val HIGH_SUMMER = 25.0
const val LOW_WINTER = 20.0
const val HIGH_WINTER = 22.0

data class TemperatureRange(
    val celsius: Double,
    val kelvin: Double,
    val fahrenheit: Double
)

val summerRange = TemperatureRange(LOW_SUMMER, LOW_SUMMER + 273.15, LOW_SUMMER * 9 / 5 + 32) to
        TemperatureRange(HIGH_SUMMER, HIGH_SUMMER + 273.15, HIGH_SUMMER * 9 / 5 + 32)

val winterRange = TemperatureRange(LOW_WINTER, LOW_WINTER + 273.15, LOW_WINTER * 9 / 5 + 32) to
        TemperatureRange(HIGH_WINTER, HIGH_WINTER + 273.15, HIGH_WINTER * 9 / 5 + 32)

class ThermometerViewModel : ViewModel() {
    enum class Season {
        WINTER,
        SUMMER
    }

    enum class ScaleType {
        CELSIUS,
        KELVIN,
        FAHRENHEIT
    }

    fun processInput(tempValue: Double, season: Season, scaleType: ScaleType): String {
        var result: String = "The temperature is "

        val displayedTempValue = when (scaleType) {
            ScaleType.CELSIUS -> tempValue
            ScaleType.KELVIN -> tempValue + 273.15
            ScaleType.FAHRENHEIT -> tempValue * 9 / 5 + 32
        }

        result += when (scaleType) {
            ScaleType.CELSIUS -> " $displayedTempValue C"
            ScaleType.KELVIN -> " $displayedTempValue K"
            ScaleType.FAHRENHEIT -> " $displayedTempValue F"
        }

        val (lowTemp, highTemp) = when (season) {
            Season.SUMMER -> summerRange
            Season.WINTER -> winterRange
        }

        val (low, high) = when (scaleType) {
            ScaleType.CELSIUS -> lowTemp.celsius to highTemp.celsius
            ScaleType.KELVIN -> lowTemp.kelvin to highTemp.kelvin
            ScaleType.FAHRENHEIT -> lowTemp.fahrenheit to highTemp.fahrenheit
        }

        result += "\n\nThe comfortable temperature is from $low to $high "
        result += when (scaleType) {
            ScaleType.CELSIUS -> "˚C."
            ScaleType.KELVIN -> "˚K."
            ScaleType.FAHRENHEIT -> "˚F."
        }

        val correctUp = if (displayedTempValue < low) low - displayedTempValue else 0.0
        val correctDown = if (displayedTempValue > high) displayedTempValue - high else 0.0

        result += when {
            correctUp > 0 -> "\n\nPlease, make it warmer by $correctUp degrees."
            correctDown > 0 -> "\n\nPlease, make it cooler by $correctDown degrees."
            else -> "\n\nTemperature is fine. Enjoy."
        }

        return result
    }
}