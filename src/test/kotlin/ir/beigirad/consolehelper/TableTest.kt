package ir.beigirad.consolehelper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TableTest {
    private val row1 = arrayOf("T1", "Header2", "TopTopTopTop")
    private val row2 = arrayOf("T1", "dfkjsdh,mnkhkfkjdshfk", "wedkfjdsldfdsfkfjdslfjldsfjlsdfjl")
    private val row3 = arrayOf("T1", "TtTTTTTTTTTTOOOOTTTTTTTTTTT", "hjhgjwedkfjdsldfRTYUIOUOIIOUYIUYIUYIU")

    @Test
    fun `table works with the same delimiter file`() {
        val table = Table(" | ").apply {
            addRow(*row1)
            addRow(*row2)
            addRow(*row3)
        }.toString()

        val expected = """
            T1 | Header2                     | TopTopTopTop                          | 
            T1 | dfkjsdh,mnkhkfkjdshfk       | wedkfjdsldfdsfkfjdslfjldsfjlsdfjl     | 
            T1 | TtTTTTTTTTTTOOOOTTTTTTTTTTT | hjhgjwedkfjdsldfRTYUIOUOIIOUYIUYIUYIU | 
        """.trimIndent()

        Assertions.assertEquals(expected, table)
    }

    @Test
    fun `table with different delimiter`() {
        val table = Table(" : ", "|", "").apply {
            addRow(*row1)
            addRow(*row2)
            addRow(*row3)
        }.toString()

        val expected = """
            T1 : Header2                    |TopTopTopTop                         
            T1 : dfkjsdh,mnkhkfkjdshfk      |wedkfjdsldfdsfkfjdslfjldsfjlsdfjl    
            T1 : TtTTTTTTTTTTOOOOTTTTTTTTTTT|hjhgjwedkfjdsldfRTYUIOUOIIOUYIUYIUYIU
        """.trimIndent()

        Assertions.assertEquals(expected, table)
    }
}