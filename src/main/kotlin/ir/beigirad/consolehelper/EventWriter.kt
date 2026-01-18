package ir.beigirad.consolehelper

import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.jline.utils.InfoCmp
import java.io.Closeable
import java.io.Flushable
import java.io.PrintWriter

class EventWriter private constructor(
    private val terminal: Terminal,
    private val writer: PrintWriter = terminal.writer(),
    private val showIndicator: Boolean = true,
) : Flushable by writer,
    Closeable by writer {

    @JvmOverloads
    constructor(
        terminal: Terminal? = null,
        showIndicator: Boolean = true
    ) : this(
        terminal = (terminal ?: TerminalBuilder.builder().system(true).build()),
        showIndicator = showIndicator,
    )

    private var lastProgress: ProgressEvent? = null
    private var progressPhase = 0

    fun printlnProgress(message: String) {
        internalPrint(ProgressEvent(message))
    }

    fun println(message: String = "") {
        internalPrint(PrintEvent(message, newLine = true))
    }

    fun print(message: String) {
        internalPrint(PrintEvent(message, newLine = false))
    }

    fun errorln(message: String) {
        internalPrint(PrintEvent("${colors.red}$message${colors.reset}", newLine = true))
    }

    fun error(message: String) {
        internalPrint(PrintEvent("${colors.red}$message${colors.reset}", newLine = false))
    }

    private fun internalPrint(event: Event) {
        when (event) {
            is PrintEvent -> {
                lastProgress?.clear() // clear if it exists

                if (event.newLine)
                    writer.println(event.line)
                else
                    writer.print(event.line)

                lastProgress?.print() // keep progress at bottom
            }

            is ProgressEvent -> {
                if (event == lastProgress) return

                lastProgress?.clear() // clear if it exists

                event.print()
                lastProgress = event
            }
        }

        writer.flush() // wait for flushing...
    }

    private val indicatorSigns = listOf("│", "/", "─", "\\")
    private fun ProgressEvent.print() {
        progressPhase = (progressPhase + 1) % indicatorSigns.size
        val indicatorSign = indicatorSigns[progressPhase]
        writer.println(indicatorSign)

        this.lines.forEach {
            writer.println(it)
        }
    }

    private fun ProgressEvent.clear() {
        repeat(this.linesCount + if (showIndicator) 1 else 0) {
            terminal.puts(InfoCmp.Capability.cursor_up, 1)
            terminal.puts(InfoCmp.Capability.carriage_return)
            terminal.puts(InfoCmp.Capability.clr_eol)
        }
    }

    private object colors {
        val red = "\u001B[31m"
        val reset = "\u001B[0m"
    }

    private sealed interface Event

    private class PrintEvent(val line: String, val newLine: Boolean) : Event

    private data class ProgressEvent(private val message: String) : Event {
        val lines = message.lines()
        val linesCount = lines.size
    }
}
