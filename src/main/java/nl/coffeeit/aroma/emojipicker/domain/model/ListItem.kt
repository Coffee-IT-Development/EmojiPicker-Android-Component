package nl.coffeeit.aroma.emojipicker.domain.model

sealed class ListItem(
    open val type: Type
) {
    open val id: String = ""

    enum class Type(
        val value: String
    ) {
        TITLE("title"),
        EMOJI("emoji"),
        UNKNOWN("unknown");

        companion object {
            fun find(ordinal: Int) = values().find { it.ordinal == ordinal } ?: UNKNOWN
        }
    }
}
