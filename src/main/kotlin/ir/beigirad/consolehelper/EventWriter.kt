package ir.beigirad.consolehelper

import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import org.jline.utils.InfoCmp
import java.io.Closeable
import java.io.Flushable
import java.io.PrintWriter

class EventWriter private constructor(
    private val terminal: Terminal,
    private val writer: PrintWriter,
) : Flushable by writer,
    Closeable by writer {

    constructor(terminal: Terminal) : this(terminal, terminal.writer())

    constructor() : this(TerminalBuilder.builder().system(true).build())

    private var lastEvent: Event? = null

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
        appendCarriage()
        lastEvent = event
        when (event) {
            is PrintEvent ->
                if (event.newLine)
                    writer.println(event.line)
                else
                    writer.print(event.line)

            is ProgressEvent ->
                event.lines.forEach {
                    writer.println(it)
                }
        }
        writer.flush()
    }

    private fun appendCarriage() {
        val last = lastEvent ?: return

        if (last is ProgressEvent) {
            repeat(last.linesCount) {
                terminal.puts(InfoCmp.Capability.cursor_up, 1)
                terminal.puts(InfoCmp.Capability.carriage_return)
                terminal.puts(InfoCmp.Capability.clr_eol)
            }
        }
    }

    private object colors {
        val red = "\u001B[31m"
        val reset = "\u001B[0m"
    }
}
