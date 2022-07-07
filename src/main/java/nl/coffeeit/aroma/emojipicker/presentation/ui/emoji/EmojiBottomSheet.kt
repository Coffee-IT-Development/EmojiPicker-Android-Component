package nl.coffeeit.aroma.emojipicker.presentation.ui.emoji

import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import nl.coffeeit.aroma.emojipicker.R
import nl.coffeeit.aroma.emojipicker.databinding.FragmentEmojiPickerBinding
import nl.coffeeit.aroma.emojipicker.di.ViewModelModule
import nl.coffeeit.aroma.emojipicker.domain.model.EmojiCategory
import nl.coffeeit.aroma.emojipicker.domain.model.EmojiItem
import nl.coffeeit.aroma.emojipicker.domain.model.ListItem
import nl.coffeeit.aroma.emojipicker.domain.model.Title
import nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.EmojiAdapter
import nl.coffeeit.aroma.emojipicker.presentation.adapter.emoji.EmojiItemClickListener

private const val BOTTOM_SHEET_HEIGHT_PERCENTAGE = 0.8

class EmojiBottomSheet(
    private val onAction: (emojiItem: EmojiItem) -> Unit
) : BaseBottomSheet(), EmojiItemClickListener {

    private lateinit var binding: FragmentEmojiPickerBinding

    private val adapter = EmojiAdapter(this)
    private var addedFirstEmoji = false
    private var gridLayoutManager: GridLayoutManager? = null
    private var highlightEnabled = true
    private var list: List<ListItem>? = null
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            var endPosition = (gridLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            if (endPosition == 1 && dy == 0 && addedFirstEmoji) {
                endPosition = 0
                addedFirstEmoji = false
                binding.listEmojis.post {
                    binding.listEmojis.scrollToPosition(endPosition)
                }
            }
            val item = list?.getOrNull(endPosition)
            if (item is Title && highlightEnabled) {
                highlightIcon(item.category)
            }
        }
    }
    private val spanCount: Int by lazy { resources.getInteger(R.integer.emoji_columns) }
    private val viewModel: EmojiViewModel by lazy {
        EmojiViewModel(
            ViewModelModule.provideEmojiRepository(),
            ViewModelModule.provideSharedPreferencesHelper(requireContext()),
            requireContext()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)
        binding = FragmentEmojiPickerBinding.inflate(LayoutInflater.from(requireContext()), null, false)
        bottomSheetDialog.setContentView(binding.root)

        setRecyclerView()
        setObservers()
        setListeners(bottomSheetDialog)

        return bottomSheetDialog
    }

    private fun setRecyclerView() {
        gridLayoutManager = GridLayoutManager(requireContext(), spanCount)
        gridLayoutManager?.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    ListItem.Type.EMOJI.ordinal -> 1
                    ListItem.Type.TITLE.ordinal -> spanCount
                    else -> -1
                }
            }
        }
        binding.listEmojis.layoutManager = gridLayoutManager
        binding.listEmojis.addOnScrollListener(scrollListener)
        binding.listEmojis.adapter = adapter
        binding.showRecent = !viewModel.recentEmojisIsEmpty()
    }

    private fun setObservers() {
        viewModel.emojis.observe(this) { list ->
            val titles = list.filterIsInstance<Title>()
            this.list = list
            binding.actionRecent.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.RECENT)
            }
            binding.actionSmileys.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.SMILEYS_AND_PEOPLE)
            }
            binding.actionNature.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.ANIMALS_AND_NATURE)
            }
            binding.actionFood.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.FOOD_AND_DRINK)
            }
            binding.actionActivity.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.ACTIVITY)
            }
            binding.actionTravel.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.TRAVEL_AND_PLACES)
            }
            binding.actionObjects.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.OBJECTS)
            }
            binding.actionSymbols.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.SYMBOLS)
            }
            binding.actionFlags.setOnClickListener {
                scrollTo(titles, list, EmojiCategory.FLAGS)
            }
            adapter.submitList(list)
        }

        viewModel.addedFirstEmoji.observe(this) {
            binding.showRecent = true
            addedFirstEmoji = true
        }
    }

    private fun setListeners(bottomSheetDialog: Dialog) {
        val parentLayout = binding.root.parent as View
        bottomSheetDialog.setOnShowListener {
            setupFullHeight(parentLayout)
        }

        binding.itemInputSearch.inputSearch.doAfterTextChanged {
            viewModel.search(it.toString())
            highlightEnabled = it?.isBlank() == true
            if (it?.isNotBlank() == true) {
                removeAllHighlights()
            }
            if (it?.isEmpty() == true) {
                binding.actionRecent.setColorFilter(ContextCompat.getColor(requireContext(), R.color.accent), android.graphics.PorterDuff.Mode.SRC_IN)
            }
        }
        binding.actionRecent.setColorFilter(ContextCompat.getColor(requireContext(), R.color.accent), android.graphics.PorterDuff.Mode.SRC_IN)
    }

    private fun scrollTo(titles: List<Title>, list: List<ListItem>, category: EmojiCategory) {
        val input = binding.itemInputSearch.inputSearch
        if (input.text.isNotBlank()) input.setText("")
        val item: ListItem? = titles.find { it.category == category }
        val index = list.indexOf(item)
        gridLayoutManager?.scrollToPositionWithOffset(index, 0)
        highlightIcon(category)
    }

    private fun highlightIcon(category: EmojiCategory) {
        val view = when (category) {
            EmojiCategory.RECENT -> binding.actionRecent
            EmojiCategory.SMILEYS_AND_PEOPLE -> binding.actionSmileys
            EmojiCategory.ANIMALS_AND_NATURE -> binding.actionNature
            EmojiCategory.FOOD_AND_DRINK -> binding.actionFood
            EmojiCategory.ACTIVITY -> binding.actionActivity
            EmojiCategory.TRAVEL_AND_PLACES -> binding.actionTravel
            EmojiCategory.OBJECTS -> binding.actionObjects
            EmojiCategory.SYMBOLS -> binding.actionSymbols
            EmojiCategory.FLAGS -> binding.actionFlags
        }
        removeAllHighlights()
        view.setColorFilter(
            ContextCompat.getColor(requireContext(), R.color.accent),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    private fun removeAllHighlights() {
        binding.actionRecent.colorFilter = null
        binding.actionSmileys.colorFilter = null
        binding.actionNature.colorFilter = null
        binding.actionFood.colorFilter = null
        binding.actionActivity.colorFilter = null
        binding.actionTravel.colorFilter = null
        binding.actionObjects.colorFilter = null
        binding.actionSymbols.colorFilter = null
        binding.actionFlags.colorFilter = null
    }

    private fun setupFullHeight(bottomSheet: View) {
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        behavior.isDraggable = true
        behavior.skipCollapsed = true
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        layoutParams.height = (windowHeight * BOTTOM_SHEET_HEIGHT_PERCENTAGE).toInt()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onDismiss(dialog: DialogInterface) {
        binding.itemInputSearch.inputSearch.setText("")
        super.onDismiss(dialog)
    }

    private fun getWindowHeight(): Int {
        // Calculate window height for fullscreen use
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.getSystemService(WindowManager::class.java)?.currentWindowMetrics?.bounds?.height() ?: 0
        } else {
            val displayMetrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            displayMetrics.heightPixels
        }
    }

    companion object {
        const val TAG = "EmojiModalBottomSheet"
    }

    override fun onItemClick(emojiItem: EmojiItem) {
        onAction.invoke(emojiItem)
        viewModel.addToRecents(emojiItem)
    }
}