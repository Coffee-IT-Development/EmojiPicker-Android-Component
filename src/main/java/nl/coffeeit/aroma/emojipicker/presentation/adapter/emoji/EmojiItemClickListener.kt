package nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji

import nl.coffeeit.aroma.emojipicker.domain.model.EmojiItem

interface EmojiItemClickListener {
    fun onItemClick(emojiItem: EmojiItem)
}
