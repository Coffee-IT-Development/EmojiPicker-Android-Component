package nl.coffeeit.aroma.emojipicker.domain.model

data class Emoji(
    override val id: String,
    val emojiItem: EmojiItem
) : ListItem(Type.EMOJI)