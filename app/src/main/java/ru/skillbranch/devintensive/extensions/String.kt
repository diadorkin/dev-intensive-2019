package ru.skillbranch.devintensive.extensions

fun String?.truncate(count: Int = 16): String? {
    if (count >= this?.trim()?.length!!) return this.trim()

    return this.trim().replace("\\s+".toRegex(), " ").take(count).trim().let {
        return when {
            it.length > 1  -> it.plus("...")
            it.length == 1 -> it
            else -> ""
        }
    }
}

fun String?.stripHtml(): String? {
    return this
        ?.replace("""<.*?>""".toRegex(), "")
        ?.replace("""&(#\d+?|\w+?);""".toRegex(), "")
        ?.split(""" +""".toRegex())
        ?.joinToString(" ")

}