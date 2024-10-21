package com.mockingb.logger

import android.util.Log

object AppLogger {

    enum class LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    fun log(
        level: LogLevel,
        tag: String = "Smart Calculator",
        message: String,
        throwable: Throwable? = null
    ) {
        when (level) {
            LogLevel.DEBUG -> Log.d(tag, message, throwable)
            LogLevel.INFO -> Log.i(tag, message, throwable)
            LogLevel.WARN -> Log.w(tag, message, throwable)
            LogLevel.ERROR -> Log.e(tag, message, throwable)
        }
    }

    fun debug(tag: String = "Smart Calculator", message: String, throwable: Throwable? = null) {
        log(LogLevel.DEBUG, tag, message, throwable)
    }

    fun info(tag: String = "Smart Calculator", message: String, throwable: Throwable? = null) {
        log(LogLevel.INFO, tag, message, throwable)
    }

    fun warn(tag: String = "Smart Calculator", message: String, throwable: Throwable? = null) {
        log(LogLevel.WARN, tag, message, throwable)
    }

    fun error(tag: String = "Smart Calculator", message: String, throwable: Throwable? = null) {
        log(LogLevel.ERROR, tag, message, throwable)
    }
}
