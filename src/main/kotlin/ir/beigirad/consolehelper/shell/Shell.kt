package ir.beigirad.consolehelper.shell

import eu.jrie.jetbrains.kotlinshell.shell.Shell
import eu.jrie.jetbrains.kotlinshell.shell.shell
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach

@ExperimentalCoroutinesApi
fun String.runCommand(): String =
    buildString {
        shell { pipeline { this@runCommand.process() pipe this@buildString } }
    }

@ExperimentalCoroutinesApi
suspend fun String.runCommand(
    shell: Shell,
    log: (String) -> Unit,
    error: (String) -> Unit
) {
    with(shell) {
        this@runCommand.process() forkErr pipelineFork { process ->
            process pipe { ctx ->
                ctx.stdin.consumeEach {
                    error(it.readText())
                }
            }
            pipe { ctx ->
                ctx.stdin.consumeEach {
                    log(it.readText())
                }
            }
        }
    }
}