package nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.coffeeit.aroma.emojipicker.domain.model.ListItem

class EmojiAdapter(
    private val clickListener: EmojiItemClickListener,
    private val cardViewHolderFactory: CardViewHolderFactory = CardViewHolderFactory(clickListener)
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(DiffCallback(cardViewHolderFactory)) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        cardViewHolderFactory.getBinding(getItem(position), holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewTypeOrdinal: Int): RecyclerView.ViewHolder {
        val type = ListItem.Type.find(viewTypeOrdinal)
        return cardViewHolderFactory.getViewHolder(parent, type)
    }

    override fun getItemViewType(position: Int) = getItem(position).type.ordinal

    class DiffCallback(
        private val cardViewHolderFactory: CardViewHolderFactory
    ) : DiffUtil.ItemCallback<ListItem>() {

        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return cardViewHolderFactory.areItemsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return cardViewHolderFactory.areContentsTheSame(oldItem, newItem)
        }
    }
}
