package nl.coffeeit.aroma.emojipicker.domain.model

import com.google.gson.annotations.SerializedName

data class EmojiItem(
    @SerializedName("emoji") val emoji : String,
    @SerializedName("name") val name : String,
    @SerializedName("slug") val slug : String
) {
    val unicode get() = String.format("u+%04x", emoji.codePointAt(0)).uppercase()
}