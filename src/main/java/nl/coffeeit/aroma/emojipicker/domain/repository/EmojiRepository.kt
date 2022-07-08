package nl.coffeeit.aroma.emojipicker.domain.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import nl.coffeeit.aroma.emojipicker.R
import javax.inject.Singleton

@Singleton
class EmojiRepository {
    private val emojis: Map<String, List<LinkedTreeMap<String, String>>>? = null
    fun getEmojis(context: Context): Map<String, List<LinkedTreeMap<String, String>>>? {
        if (emojis != null) return emojis
        val json = context.resources.openRawResource(R.raw.emojis_by_group)
            .bufferedReader().use { it.readText() }
        var map: Map<String, List<LinkedTreeMap<String, String>>> = HashMap()
        map = Gson().fromJson(json, map.javaClass)
        return map
    }
}