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