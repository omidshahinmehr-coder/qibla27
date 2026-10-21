package com.qibla.prayertimes.util

/**
 * Digit style per app language: Persian and Arabic show Persian digits (۰۱۲۳۴۵۶۷۸۹), English
 * (and anything else) keeps Latin digits (0123456789).
 */
object Digits {
    private const val PERSIAN = "۰۱۲۳۴۵۶۷۸۹"

    /** Set to false to show Arabic-Indic digits (٠١٢٣٤٥٦٧٨٩) for Arabic instead. */
    private const val ARABIC_USES_PERSIAN_DIGITS = true
    private const val ARABIC_INDIC = "٠١٢٣٤٥٦٧٨٩"

    /** True when this language shows non-Latin (Persian) digits. */
    fun usesNativeDigits(language: String): Boolean = language == "fa" || language == "ar"

    fun localize(text: String, language: String): String {
        val table = when {
            language == "fa" -> PERSIAN
            language == "ar" -> if (ARABIC_USES_PERSIAN_DIGITS) PERSIAN else ARABIC_INDIC
            else -> return text
        }
        val sb = StringBuilder(text.length)
        for (c in text) sb.append(if (c in '0'..'9') table[c - '0'] else c)
        return sb.toString()
    }
}
