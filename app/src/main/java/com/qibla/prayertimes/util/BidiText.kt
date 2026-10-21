package com.qibla.prayertimes.util

/**
 * Widgets inherit their text direction from the system language, so a Persian/Arabic date that
 * starts with a digit ("9 جمادی الاول 1448 ه.ق") gets its first number thrown to the wrong side
 * when the system language is left-to-right. Wrapping the string in an invisible right-to-left
 * embedding (RLE ... PDF) makes the whole date lay out right-to-left: day, month, year in order.
 * English text is returned unchanged.
 */
object BidiText {
    fun forLanguage(text: String, language: String): String =
        if (text.isNotBlank() && (language == "fa" || language == "ar")) "\u202B${Digits.localize(text, language)}\u202C" else text
}
