package nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.view_holder

import androidx.recyclerview.widget.RecyclerView
import nl.coffeeit.aroma.emojipicker.databinding.ItemTitleBinding
import nl.coffeeit.aroma.emojipicker.domain.model.Title
import nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.EmojiItemClickListener

class TitleViewHolder(
    private val binding: ItemTitleBinding,
    private val listener: EmojiItemClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(title: Title) {
        binding.apply {
            item = title
            executePendingBindings()
        }
    }
}
