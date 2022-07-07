package nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.view_holder

import androidx.recyclerview.widget.RecyclerView
import nl.coffeeit.aroma.emojipicker.databinding.ItemTitleBinding
import nl.coffeeit.aroma.emojipicker.domain.model.Title

class TitleViewHolder(
    private val binding: ItemTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(title: Title) {
        binding.apply {
            item = title
            executePendingBindings()
        }
    }
}
