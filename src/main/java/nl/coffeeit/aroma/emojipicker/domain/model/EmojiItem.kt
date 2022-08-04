package nl.coffeeit.aroma.emojipicker.domain.model

import com.google.gson.annotations.SerializedName
import nl.coffeeit.aroma.emojipicker.util.UNICODE_HEX_PREFIX
import java.util.*

data class EmojiItem(
    @SerializedName("emoji") val emoji: String,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String
) {
    val unicode get() = run {
        val stringBuilder = StringBuilder()
        var offset = 0
        while (offset < emoji.length) {
            val codePoint = emoji.codePointAt(offset)
            stringBuilder.append(UNICODE_HEX_PREFIX + Integer.toHexString(codePoint).uppercase(Locale.getDefault()))
            offset += Character.charCount(codePoint)
        }
        stringBuilder.toString()
    }
}