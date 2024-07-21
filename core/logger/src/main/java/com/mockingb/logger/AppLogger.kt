package com.mockingb.logger

object AppLogger {

    enum class LogLevel {
        INFO, WARNING, ERROR
    }

    fun log(message: String, level: LogLevel = LogLevel.INFO) {
        val stackTrace = Thread.currentThread().stackTrace
        val currentElement = stackTrace[4] // 0 - getStackTrace, 1 - log, 2 - the method calling log
        val fileName = currentElement.fileName
        val className = currentElement.className
        val lineNumber = currentElement.lineNumber

        val logMessage = "${level.name}: ($fileName:$lineNumber) [$className] - $message"
        println(logMessage)
    }
}
