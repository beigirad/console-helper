package ir.beigirad.consolehelper

class ArgProcessor(rawArgs: Array<String>) {
    private val regex = Regex("""^--([^=]+)(?:=(.*))?$""", RegexOption.DOT_MATCHES_ALL)
    private val arg = rawArgs.mapNotNull {
        val matches = regex.find(it) ?: return@mapNotNull null
        matches.groupValues[1] to matches.groupValues[2]
    }.toMap()

    fun getOrNull(key: String): String? = arg[key]

    fun contains(key: String): Boolean = arg.containsKey(key)

    operator fun get(key: String): String {
        check(contains(key)) { "Required '$key' was absent." }
        return checkNotNull(getOrNull(key)) { "Required '$key' exists but is null." }
    }

    override fun toString(): String = "Args(${arg})"
}