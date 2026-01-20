package ir.beigirad.consolehelper

class Tree(private val head: String) {
    private val children: MutableList<Tree> = mutableListOf()

    fun addNode(node: Tree): Tree = node.also(children::add)

    fun addNode(node: String): Tree = addNode(Tree(node))

    override fun toString(): String = buildString {
        renderInto(sb = this, prefix = "", isLast = true, isRoot = true)
    }

    private fun renderInto(
        sb: StringBuilder,
        prefix: String,
        isLast: Boolean,
        isRoot: Boolean
    ) {
        if (isRoot) {
            sb.append(head)
        } else {
            sb.append(prefix)
            sb.append(if (isLast) TAIL else CONT)
            sb.append(' ')
            sb.append(head)
        }

        val nextPrefix = if (isRoot) "" else prefix + if (isLast) INDENT else PIPE_INDENT

        children.forEachIndexed { i, child ->
            sb.append('\n')
            child.renderInto(
                sb = sb,
                prefix = nextPrefix,
                isLast = (i == children.lastIndex),
                isRoot = false
            )
        }
    }

    companion object {
        private const val INDENT = "    "
        private const val PIPE_INDENT = "│   "
        private const val TAIL = "└──"
        private const val CONT = "├──"
    }

    @DslMarker
    annotation class TreeDsl

    @TreeDsl
    class TreeBuilder(head: String) {
        private val tree = Tree(head)

        fun node(name: String) {
            tree.addNode(name)
        }

        fun node(name: String, block: TreeBuilder.() -> Unit) {
            val builder = TreeBuilder(name).apply(block)
            tree.addNode(builder.tree)
        }

        fun build(): Tree = tree
    }
}

inline fun buildTree(head: String, block: Tree.TreeBuilder.() -> Unit): Tree =
    Tree.TreeBuilder(head).apply(block).build()
