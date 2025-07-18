package ir.beigirad.jlinehelper

import org.jline.terminal.Terminal
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

    private var lastEvent: Event? = null

    fun printlnProgress(message: String) {
        internalPrint(ProgressEvent(message))
    }

    fun println(message: String = "") {
        internalPrint(PrintEvent(message))
    }

    private fun internalPrint(event: Event) {
        appendCarriage()
        lastEvent = event
        when (event) {
            is PrintEvent ->
                writer.println(event.line)

            is ProgressEvent ->
                event.lines.forEach {
                    writer.println(it)
                }
        }
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
}
