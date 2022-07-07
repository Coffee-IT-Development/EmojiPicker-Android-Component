package nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.view_holder

import androidx.recyclerview.widget.RecyclerView
import nl.coffeeit.aroma.emojipicker.databinding.ItemExtraTitleBinding
import nl.coffeeit.aroma.emojipicker.databinding.ItemTitleBinding
import nl.coffeeit.aroma.emojipicker.domain.model.Emoji
import nl.coffeeit.aroma.emojipicker.domain.model.Title
import nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.EmojiItemClickListener

class ExtraTitleViewHolder(
    private val binding: ItemExtraTitleBinding,
    private val listener: EmojiItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(title: Emoji) {
        binding.apply {
            item = title
            root.setOnClickListener { listener.onItemClick(title.emojiItem) }
            executePendingBindings()
        }
    }
}
