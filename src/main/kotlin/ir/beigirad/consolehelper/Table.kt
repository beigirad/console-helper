package ir.beigirad.consolehelper

class Table(
    vararg val delimiters: String = arrayOf(" "),
) {
    private val rows = mutableListOf<List<String>>()
    private val columnsMaxLength = mutableMapOf<Int, Int>()

    fun addRow(vararg cells: String) {
        rows.add(cells.toList())
        cells.forEachIndexed { index, label ->
            columnsMaxLength[index] = maxOf(columnsMaxLength.getOrElse(index) { 0 }, label.length)
        }
    }

    override fun toString(): String =
        rows.joinToString(System.lineSeparator()) { row ->
            row.withIndex().joinToString("") { (columnIndex, cell) ->
                padToStops(cell, columnIndex) + delimiterOf(columnIndex)
            }
        }

    private fun delimiterOf(column: Int): String {
        if (column == -1) return ""
        return delimiters.getOrElse(column) { delimiterOf(column - 1) }
    }

    private fun padToStops(text: String, columnIndex: Int): String {
        val delimiterLength = columnIndex * delimiterOf(columnIndex - 1).length
        val lengthOfMaxColumn = columnsMaxLength.getOrElse(columnIndex) { 0 } + delimiterLength
        val lengthOfCurrentCell = text.length + delimiterLength
        return text + " ".repeat(lengthOfMaxColumn - lengthOfCurrentCell)
    }

    @DslMarker
    annotation class TableDsl

    @TableDsl
    class TableBuilder(vararg delimiters: String = arrayOf(" ")) {
        private val tree = Table(*delimiters)

        fun row(vararg cells: String) {
            tree.addRow(*cells)
        }

        fun build(): Table = tree
    }
}

inline fun buildTable(vararg delimiters: String, block: Table.TableBuilder.() -> Unit): Table =
    Table.TableBuilder(*delimiters).apply(block).build()
