package ir.beigirad.consolehelper

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.random.Random


class EventWriterTest {
    private val writer = EventWriter()

    fun test1() = runBlocking {
        writer.println("The first line")

        launch {
            repeat(100) { counter ->
                writer.printlnProgress((0 until 10).joinToString("\n") { "========> Progress $counter  [$it/10] " })
                delay(Random.nextLong(5, 50))
            }
        }

        launch {
            repeat(100) { counter ->
                writer.println((0 until 10).joinToString("\n") { "PRINTER $counter  [$it/10] " })
                delay(Random.nextLong(5, 50))
            }
        }

        writer.println("The last line")
    }

    @Test
    fun test2() = runBlocking {
        writer.println("The first line")

        launch {
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
    }
}