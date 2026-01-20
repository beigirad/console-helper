package ir.beigirad.consolehelper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TreeTest {
    @Test
    fun `one level tree shows correctly`() {
        val tree = buildTree("Head") {
            node("Child 1")
            node("Child 2")
            node("Child 3")
            node("Child 4")
            node("Child 5")
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
        val tree = buildTree("Head") {
            node("First 1")
            node("First 2")
            node("First 3")
            node("First 4")
            node("First 5") {
                node("Second 1")
                node("Second 2")
                node("Second 3")
                node("Second 4")
            }
            build()
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
        val tree = buildTree("Head") {
            node("First 1")
            node("First 2")
            node("First 3")
            node("First 4")
            node("First 5") {
                node("Second 1")
                node("Second 2")
                node("Second 3")
                node("Second 4") {
                    node("Third 1")
                    node("Third 2")
                    node("Third 3")
                    node("Third 4")
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
        val tree = buildTree("Head") {
            node("First 1")
            node("First 2")
            node("First 3")
            node("First 4")
            node("First 5") {
                node("Second 1")
                node("Second 2")
                node("Second 3")
                node("Second 4")
            }
            node("First 6")
            node("First 7")
            node("First 8")
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
        val tree = buildTree("Head") {
            node("First 1")
            node("First 2")
            node("First 3")
            node("First 4")
            node("First 5") {
                node("Second 1")
                node("Second 2") {
                    node("Third 1")
                    node("Third 2")
                    node("Third 3")
                    node("Third 4")
                }
                node("Second 3")
                node("Second 4")
            }
            node("First 6")
            node("First 7")
            node("First 8")
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
        val tree = buildTree("Head") {
            node("First 1")
            node("First 2")
            node("First 3")
            node("First 4")
            node("First 5") {
                node("Second 1")
                node("Second 2") {
                    node("Third 1")
                    node("Third 2")
                    node("Third 3")
                    node("Third 4") {
                        node("Forth 1")
                        node("Forth 2")
                    }
                }
                node("Second 3")
                node("Second 4")
            }
            node("First 6")
            node("First 7")
            node("First 8") {
                node("SecondOf8 1")
                node("SecondOf8 2")
                node("SecondOf8 3")
                node("SecondOf8 4") {
                    node("ThirdOf4 1")
                    node("ThirdOf4 2")
                    node("ThirdOf4 3")
                    node("ThirdOf4 4")
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