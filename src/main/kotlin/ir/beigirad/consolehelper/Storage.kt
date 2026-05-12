package ir.beigirad.consolehelper

import java.nio.file.Path
import kotlin.io.path.createFile
import kotlin.io.path.notExists
import kotlin.io.path.readLines
import kotlin.io.path.writeText

class Storage(private val source: Path) {
    init {
        if (source.notExists()) source.createFile()
    }

    var values: List<String> = emptyList()
        get() {
            if (field.isEmpty()) {
                field = source.readLines().filter { it.isNotEmpty() }
            }
            return field
        }
        set(value) {
            field = value.filter { it.isNotBlank() }
            source.writeText(field.joinToString(System.lineSeparator()))
        }

    operator fun plusAssign(value: String) {
        values = values + value
    }

    operator fun minusAssign(value: String) {
        values = values - value
    }

    val size: Int get() = values.size
}