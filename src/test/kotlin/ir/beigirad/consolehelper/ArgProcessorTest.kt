package ir.beigirad.consolehelper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull
import org.junit.jupiter.api.assertThrows

class ArgProcessorTest {

    @Test
    fun `test get or null`() {
        val processor = ArgProcessor(arrayOf("--key1=value1", "--key2=value2"))
        assertEquals("value1", processor.getOrNull("key1"))
        assertEquals("value2", processor.getOrNull("key2"))
        assertNull(processor.getOrNull("missingKey"))
    }

    @Test
    fun `test get`() {
        val processor = ArgProcessor(arrayOf("--key1=value1", "--key2=value2"))
        assertEquals("value1", processor["key1"])
        assertEquals("value2", processor["key2"])
    }

    @Test
    fun `test get or throws-exception`() {
        val processor = ArgProcessor(arrayOf("--key1=value1", "--key2=value2"))
        val exception = assertThrows<IllegalStateException> { processor["missingKey"] }
        assertEquals("Required 'missingKey' was null.", exception.message)
    }

    @Test
    fun `test to-string`() {
        val processor = ArgProcessor(arrayOf("--key1=value1", "--key2=value2"))
        assertEquals("Args({key1=value1, key2=value2})", processor.toString())
    }

    @Test
    fun `test values that are multiline`() {
        val value = "val\nue\n1\n"
        val processor = ArgProcessor(arrayOf("--key1=$value", "--key2=value2"))
        assertEquals(value, processor.getOrNull("key1"))
        assertEquals("value2", processor["key2"])
    }

    @Test
    fun `test values that have spaces`() {
        val value = "val 1 "
        val processor = ArgProcessor(arrayOf("--key1=$value", "--key2=value2"))
        assertEquals(value, processor.getOrNull("key1"))
        assertEquals("value2", processor["key2"])
    }

    @Test
    fun `test values that have '='`() {
        val processor = ArgProcessor(arrayOf("--key1=val=ue1", "--key2=value2"))
        assertEquals("val=ue1", processor.getOrNull("key1"))
        assertEquals("value2", processor["key2"])
    }

    @Test
    fun `test values that have '--'`() {
        val processor = ArgProcessor(arrayOf("--key1=val=ue--1=", "--key2=value2"))
        assertEquals("val=ue--1=", processor.getOrNull("key1"))
        assertEquals("value2", processor["key2"])
    }
}