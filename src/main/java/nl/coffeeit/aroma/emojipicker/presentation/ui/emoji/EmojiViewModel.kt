package nl.coffeeit.aroma.emojipicker.presentation.ui.emoji

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import nl.coffeeit.aroma.emojipicker.data.local.SharedPreferencesHelper
import nl.coffeeit.aroma.emojipicker.domain.model.*
import nl.coffeeit.aroma.emojipicker.domain.repository.EmojiRepository

class EmojiViewModel(
    private val emojiRepository: EmojiRepository,
    private val sharedPreferencesHelper: SharedPreferencesHelper,
    private val context: Context
) : ViewModel() {

    private val orderById = listOf(
        EmojiCategory.RECENT,
        EmojiCategory.SMILEYS_AND_PEOPLE,
        EmojiCategory.ANIMALS_AND_NATURE,
        EmojiCategory.FOOD_AND_DRINK,
        EmojiCategory.ACTIVITY,
        EmojiCategory.TRAVEL_AND_PLACES,
        EmojiCategory.OBJECTS,
        EmojiCategory.SYMBOLS,
        EmojiCategory.FLAGS
    )

    private val _query = MutableLiveData("")

    val emojis = _query.switchMap { query ->
        liveData {
            val list = mutableListOf<ListItem>()
            val emojiCollection = emojiRepository.getEmojis(context)
            val sortedEntries = emojiCollection?.entries?.sortedBy { entry ->
                orderById.find { it.key == entry.key }
            }
            val recentEmojis = sharedPreferencesHelper.getRecentEmojis()
            if (query.isNullOrEmpty() && recentEmojis.isNotEmpty()) {
                list.add(Title(EmojiCategory.RECENT))
                recentEmojis.forEach { recentEmoji ->
                    list.add(Emoji(recentEmoji.slug, recentEmoji))
                }
            }
            sortedEntries?.forEach { emoji ->
                val filteredEmojis =
                    if (query.isNullOrEmpty()) emoji.value else emoji.value.filter {
                        it["name"]?.contains(query) == true
                    }
                if (filteredEmojis.isNotEmpty()) {
                    EmojiCategory.values().find { it.key == emoji.key }?.let { category ->
                        list.add(Title(category))
                    }
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

    fun clearQuery() = _query.postValue("")

    fun recentEmojisIsEmpty() = sharedPreferencesHelper.getRecentEmojis().isEmpty()

    fun addToRecents(emojiItem: EmojiItem) {
        sharedPreferencesHelper.addRecentEmoji(emojiItem)
    }
}