package nl.coffeeit.aroma.emojipicker.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class EmojiItem(
    @SerializedName("emoji") val emoji: String,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String
) {
    val unicode get() = run {
        val stringBuilder = StringBuilder(emoji.length)
        val formatter = Formatter(stringBuilder)
        for (char in emoji.toCharArray()) {
            if (char.code < 128) {
                stringBuilder.append(char)
            } else {
                formatter.format("\\u%04x", char.code)
            }
        }
        stringBuilder.toString()
    }
}