package com.mockingb.prime


import androidx.lifecycle.ViewModel
import kotlin.math.sqrt

open class PrimeViewModel : ViewModel() {
    fun checkPrime(number: Int): Boolean {
        if (number == 1 || number == 2) return true
        for (i in 2..sqrt(number.toDouble()).toInt()) {
            if (number % i == 0) {
                return false
            }
        }
        return true
    }

    open fun reverseNumber(number: String): String {
        return number.reversed()
    }

    open fun processNumber(rawNumber: String, isReversed: Boolean): String {
        return try {
            var number: String = if (isReversed) reverseNumber(rawNumber) else rawNumber
            var result: String = ""

            var currentNumber: String = ""
            for (currentIndex in number.indices) {
                currentNumber += number[currentIndex]
                result += currentNumber
                if (checkPrime(currentNumber.toInt())) {
                    result += ": Prime"
                }
                result += "\n"

            }
            result
        } catch (e: Exception) {
            "Number too long"
        }
    }

}