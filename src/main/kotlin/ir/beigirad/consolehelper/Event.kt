package ir.beigirad.consolehelper

sealed interface Event

internal class PrintEvent(val line: String, val newLine: Boolean) : Event

internal class ProgressEvent(message: String) : Event {
    val lines = message.lines()
    val linesCount = lines.size
}