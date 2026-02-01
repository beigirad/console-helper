@file:Repository(
    "https://repo.maven.apache.org/maven2",
    "https://jitpack.io",
)
@file:DependsOn(
    "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3",
    "com.github.beigirad:console-helper:0.8",
)

import ir.beigirad.consolehelper.EventWriter
import kotlinx.coroutines.GlobalScope
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val writer = EventWriter()
writer.println("The first line")

GlobalScope.launch {
    repeat(100) { counter ->
        writer.printlnProgress((0 until 10).joinToString("\n") { "========> Progress $counter  [$it/10] " })
        delay(Random.nextLong(5, 50))
    }
}


repeat(100) { counter ->
    writer.println((0 until 10).joinToString("\n") { "PRINTER $counter  [$it/10] " })
    Thread.sleep(Random.nextLong(5, 50))
}

writer.println("The last line")
