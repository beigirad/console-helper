package ir.beigirad.consolehelper.file

import ir.beigirad.consolehelper.shell.runCommand
import java.io.File

operator fun File.div(next: String): File = File("${this.path}/$next")

fun rootDir(): File = File("git rev-parse --show-toplevel".runCommand().trim())