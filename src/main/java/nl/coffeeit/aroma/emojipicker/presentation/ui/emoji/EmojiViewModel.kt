package nl.coffeeit.aroma.emojipicker.presentation.ui.emoji

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import nl.coffeeit.aroma.emojipicker.domain.model.Emoji
import nl.coffeeit.aroma.emojipicker.domain.model.EmojiItem
import nl.coffeeit.aroma.emojipicker.domain.model.ListItem
import nl.coffeeit.aroma.emojipicker.domain.model.Title
import nl.coffeeit.aroma.emojipicker.domain.repository.EmojiRepository

class EmojiViewModel(
    private val emojiRepository: EmojiRepository,
    private val context: Context
) : ViewModel() {

    private val orderById = listOf<String>(
        "Smileys & People",
        "Animals & Nature",
        "Food & Drink",
        "Activity",
        "Travel & Places",
        "Objects",
        "Symbols",
        "Flags"
    ).withIndex().associate { it.value to it.index }

    private val _query = MutableLiveData("")

    val emojis = _query.switchMap { query ->
        liveData {
            val list = mutableListOf<ListItem>()
            val emojiCollection = emojiRepository.getEmojis(context)
            val sortedEntries = emojiCollection?.entries?.sortedBy { orderById[it.key] }
            sortedEntries?.forEach { emoji ->
                val filteredEmojis = if (query.isNullOrEmpty()) emoji.value else emoji.value.filter { it["name"]?.contains(query) == true }
                if (filteredEmojis.isNotEmpty()) {
                    list.add(Title(emoji.key, emoji.key))
                }
                filteredEmojis.forEach { item ->
                    list.add(
                        Emoji(
                            item["slug"] ?: "",
                            EmojiItem(
                                item["emoji"] ?: "",
                                item["name"] ?: "",
                                item["slug"] ?: ""
                            )
                        ))
                }
            }
            emit(list)
        }
    }

    fun search(query: String) = _query.postValue(query)
}