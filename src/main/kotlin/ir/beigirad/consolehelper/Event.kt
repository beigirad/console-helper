package ir.beigirad.consolehelper

sealed interface Event

class PrintEvent(val line: String) : Event

class ProgressEvent(message: String) : Event {
    val lines = message.lines()
    val linesCount = lines.size
}