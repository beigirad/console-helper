package ir.beigirad.consolehelper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TreeTest {
    @Test
    fun `one level tree shows correctly`() {
        val tree = Tree("Head").apply {
            addNode("Child 1")
            addNode("Child 2")
            addNode("Child 3")
            addNode("Child 4")
            addNode("Child 5")
        }
        val expected = """
            Head
            ├── Child 1
            ├── Child 2
            ├── Child 3
            ├── Child 4
            └── Child 5
        """.trimIndent()

        Assertions.assertEquals(expected, tree.toString())
    }

    @Test
    fun `two level tree shows correctly`() {
        val tree = Tree("Head").apply {
            addNode("First 1")
            addNode("First 2")
            addNode("First 3")
            addNode("First 4")
            addNode("First 5").apply {
                addNode("Second 1")
                addNode("Second 2")
                addNode("Second 3")
                addNode("Second 4")
            }
        }

        val expected = """
            Head
            ├── First 1
            ├── First 2
            ├── First 3
            ├── First 4
            └── First 5
                ├── Second 1
                ├── Second 2
                ├── Second 3
                └── Second 4
        """.trimIndent()

        Assertions.assertEquals(expected, tree.toString())
    }

    @Test
    fun `tree level tree shows correctly`() {
        val tree = Tree("Head").apply {
            addNode("First 1")
            addNode("First 2")
            addNode("First 3")
            addNode("First 4")
            addNode("First 5").apply {
                addNode("Second 1")
                addNode("Second 2")
                addNode("Second 3")
                addNode("Second 4").apply {
                    addNode("Third 1")
                    addNode("Third 2")
                    addNode("Third 3")
                    addNode("Third 4")
                }
            }
        }

        val expected = """
            Head
            ├── First 1
            ├── First 2
            ├── First 3
            ├── First 4
            └── First 5
                ├── Second 1
                ├── Second 2
                ├── Second 3
                └── Second 4
                    ├── Third 1
                    ├── Third 2
                    ├── Third 3
                    └── Third 4
        """.trimIndent()

        Assertions.assertEquals(expected, tree.toString())
    }

    @Test
    fun `two level tree with continues node shows correctly`() {
        val tree = Tree("Head").apply {
            addNode("First 1")
            addNode("First 2")
            addNode("First 3")
            addNode("First 4")
            addNode("First 5").apply {
                addNode("Second 1")
                addNode("Second 2")
                addNode("Second 3")
                addNode("Second 4")
            }
            addNode("First 6")
            addNode("First 7")
            addNode("First 8")
        }

        val expected = """
            Head
            ├── First 1
            ├── First 2
            ├── First 3
            ├── First 4
            ├── First 5
            │   ├── Second 1
            │   ├── Second 2
            │   ├── Second 3
            │   └── Second 4
            ├── First 6
            ├── First 7
            └── First 8
        """.trimIndent()

        Assertions.assertEquals(expected, tree.toString())
    }

    @Test
    fun `three level tree with continues node shows correctly`() {
        val tree = Tree("Head").apply {
            addNode("First 1")
            addNode("First 2")
            addNode("First 3")
            addNode("First 4")
            addNode("First 5").apply {
                addNode("Second 1")
                addNode("Second 2").apply {
                    addNode("Third 1")
                    addNode("Third 2")
                    addNode("Third 3")
                    addNode("Third 4")
                }
                addNode("Second 3")
                addNode("Second 4")
            }
            addNode("First 6")
            addNode("First 7")
            addNode("First 8")
        }

        val expected = """
            Head
            ├── First 1
            ├── First 2
            ├── First 3
            ├── First 4
            ├── First 5
            │   ├── Second 1
            │   ├── Second 2
            │   │   ├── Third 1
            │   │   ├── Third 2
            │   │   ├── Third 3
            │   │   └── Third 4
            │   ├── Second 3
            │   └── Second 4
            ├── First 6
            ├── First 7
            └── First 8
        """.trimIndent()

        Assertions.assertEquals(expected, tree.toString())
    }

    @Test
    fun `complex level tree with continues and last node shows correctly`() {
        val tree = Tree("Head").apply {
            addNode("First 1")
            addNode("First 2")
            addNode("First 3")
            addNode("First 4")
            addNode("First 5").apply {
                addNode("Second 1")
                addNode("Second 2").apply {
                    addNode("Third 1")
                    addNode("Third 2")
                    addNode("Third 3")
                    addNode("Third 4").apply {
                        addNode("Forth 1")
                        addNode("Forth 2")
                    }
                }
                addNode("Second 3")
                addNode("Second 4")
            }
            addNode("First 6")
            addNode("First 7")
            addNode("First 8").apply {
                addNode("SecondOf8 1")
                addNode("SecondOf8 2")
                addNode("SecondOf8 3")
                addNode("SecondOf8 4").apply {
                    addNode("ThirdOf4 1")
                    addNode("ThirdOf4 2")
                    addNode("ThirdOf4 3")
                    addNode("ThirdOf4 4")
                }
            }
        }

        val expected = """
            Head
            ├── First 1
            ├── First 2
            ├── First 3
            ├── First 4
            ├── First 5
            │   ├── Second 1
            │   ├── Second 2
            │   │   ├── Third 1
            │   │   ├── Third 2
            │   │   ├── Third 3
            │   │   └── Third 4
            │   │       ├── Forth 1
            │   │       └── Forth 2
            │   ├── Second 3
            │   └── Second 4
            ├── First 6
            ├── First 7
            └── First 8
                ├── SecondOf8 1
                ├── SecondOf8 2
                ├── SecondOf8 3
                └── SecondOf8 4
                    ├── ThirdOf4 1
                    ├── ThirdOf4 2
                    ├── ThirdOf4 3
                    └── ThirdOf4 4
        """.trimIndent()

        Assertions.assertEquals(expected, tree.toString())
    }

}