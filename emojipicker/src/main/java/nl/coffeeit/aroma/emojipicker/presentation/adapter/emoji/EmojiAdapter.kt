/*
 * Created by Coffee IT
 *
 * MIT License
 *
 * Copyright (c) 2022 Coffee IT
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
