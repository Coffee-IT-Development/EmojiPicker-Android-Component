package nl.coffeeit.aroma.emojipicker.domain.model

import com.google.gson.annotations.SerializedName

data class EmojiItem(
    @SerializedName("emoji") val emoji : String,
    @SerializedName("skin_tone_support") val skin_tone_support : Boolean,
    @SerializedName("name") val name : String,
    @SerializedName("slug") val slug : String,
    @SerializedName("unicode_version") val unicode_version : Double,
    @SerializedName("emoji_version") val emoji_version : Double
)