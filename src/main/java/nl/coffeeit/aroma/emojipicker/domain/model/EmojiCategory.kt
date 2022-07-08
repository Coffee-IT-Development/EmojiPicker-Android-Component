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