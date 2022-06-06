package nl.coffeeit.aroma.emojipicker.presentation.components

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.gson.internal.LinkedTreeMap
import nl.coffeeit.aroma.emojipicker.di.ViewModelModule
import nl.coffeeit.aroma.emojipicker.utils.header
import androidx.compose.ui.Modifier

private const val CELL_COUNT = 2
private val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT) }

@Composable
fun EmojiPicker(
    emojiPickerViewModel: EmojiPickerViewModel = EmojiPickerViewModel(
        ViewModelModule.provideEmojiRepository(),
        LocalContext.current
    )
) {
    val emojiCollection: Map<String, List<LinkedTreeMap<String, String>>>? by emojiPickerViewModel.emojis.observeAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 6),
        contentPadding = PaddingValues(
            vertical = 6.dp
        ),
    ) {
        emojiCollection?.entries?.forEach { emoji ->
            header {
                Text(
                    text = emoji.key,
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                )
            }
            items(
                emoji.value,
                span = { GridItemSpan(CELL_COUNT) }
            ) { item ->
                AndroidView(
                    factory = { context ->
                        AppCompatTextView(context).apply {
                            setTextColor(Color.Black.toArgb())
                            text = item["emoji"] ?: ""
                            textSize = 32F
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                        }
                    }
                )
            }
        }
    }
}

