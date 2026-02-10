package ir.beigirad.consolehelper.file

import ir.beigirad.consolehelper.shell.runCommand
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path

operator fun File.div(next: String): File = File("${this.path}/$next")

private fun rootOfGit(): String = "git rev-parse --show-toplevel".runCommand().trim()

fun rootDir(): File = File(rootOfGit())

fun rootPath(): Path = Path(rootOfGit())
