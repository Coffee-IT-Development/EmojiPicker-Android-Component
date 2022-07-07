package nl.coffeeit.aroma.emojipicker.domain.model

data class Title(
    val category: EmojiCategory
) : ListItem(Type.TITLE)
