package nl.coffeeit.aroma.emojipicker.domain.model

data class Title(
    override val id: String,
    val title: String
) : ListItem(Type.TITLE)
