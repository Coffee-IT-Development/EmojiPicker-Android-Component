package nl.coffeeit.aroma.emojipicker.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import nl.coffeeit.aroma.emojipicker.domain.model.EmojiItem

private const val MAX_AMOUNT_OF_RECENT_EMOJIS = 30
private const val KEY_RECENT_EMOJIS = "key_recent_emojis"
const val KEY_SHARED_PREFERENCES = "key_aroma_emojipicker_shared_preferences"

class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {

    private val listType = object : TypeToken<List<EmojiItem>>() {}.type

    fun getRecentEmojis(): MutableList<EmojiItem> {
        val recentEmojis = sharedPreferences.getString(KEY_RECENT_EMOJIS, null)
        return if (recentEmojis.isNullOrEmpty()) {
            mutableListOf()
        } else {
            Gson().fromJson(recentEmojis, listType)
        }
    }

    fun addRecentEmoji(emojiItem: EmojiItem) {
        val recentEmojis = getRecentEmojis()
        if (recentEmojis.contains(emojiItem)) {
            recentEmojis.remove(emojiItem)
        }
        if (recentEmojis.size >= MAX_AMOUNT_OF_RECENT_EMOJIS) {
            recentEmojis.subList(MAX_AMOUNT_OF_RECENT_EMOJIS - 1, recentEmojis.size).clear()
        }
        recentEmojis.add(0, emojiItem)
        sharedPreferences.edit()
            .putString(KEY_RECENT_EMOJIS, Gson().toJson(recentEmojis, listType))
            .apply()
    }
}