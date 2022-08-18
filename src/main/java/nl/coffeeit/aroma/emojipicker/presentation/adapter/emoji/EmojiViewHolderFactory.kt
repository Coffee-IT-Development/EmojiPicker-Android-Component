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
