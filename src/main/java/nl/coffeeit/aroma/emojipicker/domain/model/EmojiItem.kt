package nl.coffeeit.aroma.emojipicker.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class EmojiItem(
    @SerializedName("emoji") val emoji: String,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String
) {
    val unicode get() = run {
        val stringBuilder = StringBuilder()
        var i = 0
        while (i < emoji.length) {
            if (Character.isSurrogate(emoji[i])) {
                val res: Int = Character.codePointAt(emoji, i)
                i++
                stringBuilder.append("0x" + Integer.toHexString(res).uppercase(Locale.getDefault()))
            } else {
                stringBuilder.append(emoji[i])
            }
            i++
        }
        stringBuilder.toString()
    }
}