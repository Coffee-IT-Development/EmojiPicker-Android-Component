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

package nl.coffeeit.aroma.emojipicker.domain.model

import androidx.annotation.StringRes
import nl.coffeeit.aroma.emojipicker.R

enum class EmojiCategory(
    val key: String,
    @StringRes
    val title: Int,
) {
    RECENT(
        "Recent",
        R.string.emojipicker_recents
    ),
    SMILEYS_AND_PEOPLE(
        "Smileys & People",
       R.string.emojipicker_smileys_and_people
    ),
    ANIMALS_AND_NATURE(
        "Animals & Nature",
        R.string.emojipicker_animals_and_nature
    ),
    FOOD_AND_DRINK(
        "Food & Drink",
        R.string.emojipicker_food_and_drink
    ),
    ACTIVITY(
        "Activity",
        R.string.emojipicker_activity
    ),
    TRAVEL_AND_PLACES(
        "Travel & Places",
        R.string.emojipicker_travel_and_places
    ),
    OBJECTS(
        "Objects",
        R.string.emojipicker_objects
    ),
    SYMBOLS(
        "Symbols",
        R.string.emojipicker_symbols
    ),
    FLAGS(
        "Flags",
        R.string.emojipicker_flags
    );
}