package ir.beigirad.consolehelper.file

import java.io.File

operator fun File.div(next: String): File = File("${this.path}/$next")