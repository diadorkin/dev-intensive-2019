package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var f = "${if (fullName?.trim().isNullOrEmpty()) null else fullName}"
        val parts: List<String>? = f?.trim()?.split(" ").filter { !it.isNullOrEmpty() }

        var firstName = parts?.getOrNull(0)?.trim()
        var lastName = parts?.getOrNull(1)?.trim()
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var res = ""

        payload.forEach {
            when (it) {
                'а' -> res += "a"
                'б' -> res += "b"
                'в' -> res += "v"
                'г' -> res += "g"
                'д' -> res += "d"
                'е' -> res += "e"
                'ё' -> res += "e"
                'ж' -> res += "zh"
                'з' -> res += "z"
                'и' -> res += "i"
                'й' -> res += "i"
                'к' -> res += "k"
                'л' -> res += "l"
                'м' -> res += "m"
                'н' -> res += "n"
                'о' -> res += "o"
                'п' -> res += "p"
                'р' -> res += "r"
                'с' -> res += "s"
                'т' -> res += "t"
                'у' -> res += "u"
                'ф' -> res += "f"
                'х' -> res += "h"
                'ц' -> res += "c"
                'ч' -> res += "ch"
                'ш' -> res += "sh"
                'щ' -> res += "sh'"
                'ъ' -> res += ""
                'ы' -> res += "i"
                'ь' -> res += ""
                'э' -> res += "e"
                'ю' -> res += "yu"
                'я' -> res += "ya"
                'А' -> res += "A"
                'Б' -> res += "B"
                'В' -> res += "V"
                'Г' -> res += "G"
                'Д' -> res += "D"
                'Е' -> res += "E"
                'Ё' -> res += "E"
                'Ж' -> res += "Zh"
                'З' -> res += "Z"
                'И' -> res += "I"
                'Й' -> res += "I"
                'К' -> res += "K"
                'Л' -> res += "L"
                'М' -> res += "M"
                'Н' -> res += "N"
                'О' -> res += "O"
                'П' -> res += "P"
                'Р' -> res += "R"
                'С' -> res += "S"
                'Т' -> res += "T"
                'У' -> res += "U"
                'Ф' -> res += "F"
                'Х' -> res += "H"
                'Ц' -> res += "C"
                'Ч' -> res += "Ch"
                'Ш' -> res += "Sh"
                'Щ' -> res += "Sh'"
                'Ъ' -> res += ""
                'Ы' -> res += "I"
                'Ь' -> res += ""
                'Э' -> res += "E"
                'Ю' -> res += "Yu"
                'Я' -> res += "Ya"
                ' ' -> if (divider != " ") res += divider else res += it
                else -> res += it
            }
        }
        return res
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var res = "";
        if (!firstName?.trim().isNullOrEmpty()) res += firstName?.capitalize()?.get(0)
        if (!lastName?.trim().isNullOrEmpty()) res += lastName?.capitalize()?.get(0)

        return if (res.isNullOrEmpty()) null else res
    }
}