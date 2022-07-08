package nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nl.coffeeit.aroma.emojipicker.databinding.ItemExtraTitleBinding
import nl.coffeeit.aroma.emojipicker.databinding.ItemTitleBinding
import nl.coffeeit.aroma.emojipicker.domain.model.Emoji
import nl.coffeeit.aroma.emojipicker.domain.model.ListItem
import nl.coffeeit.aroma.emojipicker.domain.model.Title
import nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.view_holder.ExtraTitleViewHolder
import nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.view_holder.TitleViewHolder

class CardViewHolderFactory(
    private val listener: EmojiItemClickListener
) {
    fun getViewHolder(parent: ViewGroup, type: ListItem.Type): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (type) {
            ListItem.Type.TITLE -> {
                TitleViewHolder(ItemTitleBinding.inflate(inflater, parent, false))
            }
            ListItem.Type.EMOJI -> {
                ExtraTitleViewHolder(ItemExtraTitleBinding.inflate(inflater, parent, false), listener)
            }
            ListItem.Type.UNKNOWN -> throw IllegalArgumentException("Filter out unknown types")
        }
    }

    fun getBinding(item: ListItem, viewHolder: RecyclerView.ViewHolder) {
        return when (item.type) {
            ListItem.Type.TITLE -> (viewHolder as TitleViewHolder).bind(item as Title)
            ListItem.Type.EMOJI -> (viewHolder as ExtraTitleViewHolder).bind(item as Emoji)
            ListItem.Type.UNKNOWN -> throw IllegalArgumentException("Filter out unknown types")
        }
    }

    fun areContentsTheSame(oldItemCard: ListItem, newItemCard: ListItem): Boolean {
        if (oldItemCard.type != newItemCard.type) return false
        return oldItemCard == newItemCard
    }

    fun areItemsTheSame(oldItemCard: ListItem, newItemCard: ListItem): Boolean {
        if (oldItemCard.type != newItemCard.type) return false
        return oldItemCard.id == newItemCard.id
    }
}
