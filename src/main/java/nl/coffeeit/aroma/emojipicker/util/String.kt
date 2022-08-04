package nl.coffeeit.aroma.emojipicker.util

import android.util.Log

fun String.toEmoji(): String? {
    return try {
        val stringBuilder = StringBuilder()
        split(UNICODE_HEX_PREFIX).filter { it.isNotEmpty() }.forEach { unicode ->
            stringBuilder.append(String(Character.toChars(Integer.decode("$UNICODE_HEX_PREFIX$unicode"))))
        }
        stringBuilder.toString()
    } catch (exception: Exception) {
        Log.e("String.unicodeToEmoji","Exception while parsing emoji: ${exception.message}")
        null
    }
}