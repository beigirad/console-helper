package ir.beigirad.jlinehelper

class ArgProcessor(rawArgs: Array<String>) {
    private val regex = Regex("--(.*)=(.*)", RegexOption.DOT_MATCHES_ALL)
    private val arg = rawArgs.mapNotNull {
        val matches = regex.find(it) ?: return@mapNotNull null
        matches.groupValues[1] to matches.groupValues[2]
    }.toMap()

    fun getOrNull(key: String): String? = arg[key]

    operator fun get(key: String): String =
        checkNotNull(getOrNull(key)) { "Required '$key' was null." }

    override fun toString(): String = "Args(${arg})"
}