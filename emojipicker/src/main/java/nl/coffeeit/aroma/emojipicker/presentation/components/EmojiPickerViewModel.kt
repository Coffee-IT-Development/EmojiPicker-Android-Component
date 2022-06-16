package nl.coffeeit.aroma.emojipicker.presentation.components

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.coffeeit.aroma.emojipicker.domain.repository.EmojiRepository
import javax.inject.Inject

@HiltViewModel
class EmojiPickerViewModel
@Inject constructor(
    private val emojiRepository: EmojiRepository,
    private val context: Context
): ViewModel() {
    val emojis = liveData { emit(emojiRepository.getEmojis(context)) }
}