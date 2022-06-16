package nl.coffeeit.aroma.emojipicker.domain.model

data class EmojiCollection(
    val emojis: Map<String, List<EmojiItem>>
)