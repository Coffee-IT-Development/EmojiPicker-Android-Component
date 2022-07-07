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
    private val spanCount: Int by lazy { resources.getInteger(R.integer.emoji_columns) }
    private var gridLayoutManager: GridLayoutManager? = null
    private var highlightEnabled = true
    private val viewModel: EmojiViewModel by lazy {
        EmojiViewModel(
            ViewModelModule.provideEmojiRepository(),
            requireContext()
        )
    }
    private var list: List<ListItem>? = null
    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val endPosition = (gridLayoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val item = list?.getOrNull(endPosition)
            if (item is Title && highlightEnabled) {
                highlightIcon(item.title)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState)

        binding = FragmentEmojiPickerBinding.inflate(
            LayoutInflater.from(requireContext()),
            null,
            false
        )
        binding.listEmojis.adapter = adapter

        bottomSheetDialog.setContentView(binding.root)

        val parentLayout = binding.root.parent as View

        bottomSheetDialog.setOnShowListener {
            setupFullHeight(parentLayout)
        }

        gridLayoutManager = GridLayoutManager(requireContext(), spanCount)

        binding.itemInputSearch.inputSearch.doAfterTextChanged {
            viewModel.search(it.toString())
            highlightEnabled = it?.isBlank() == true
            if (it?.isNotBlank() == true) removeAllHighlights()
            if (it?.isEmpty() == true) {
                binding.actionSmileys.setColorFilter(ContextCompat.getColor(requireContext(), R.color.accent), android.graphics.PorterDuff.Mode.SRC_IN)
            }
        }
        binding.actionSmileys.setColorFilter(ContextCompat.getColor(requireContext(), R.color.accent), android.graphics.PorterDuff.Mode.SRC_IN)

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

        viewModel.emojis.observe(this) { list ->
            val titles = list.filterIsInstance<Title>()
            this.list = list
            binding.actionSmileys.setOnClickListener {
                scrollTo(titles, list, "Smileys & People")
            }
            binding.actionNature.setOnClickListener {
                scrollTo(titles, list, "Animals & Nature")
            }
            binding.actionFood.setOnClickListener {
                scrollTo(titles, list, "Food & Drink")
            }
            binding.actionActivity.setOnClickListener {
                scrollTo(titles, list, "Activity")
            }
            binding.actionTravel.setOnClickListener {
                scrollTo(titles, list, "Travel & Places")
            }
            binding.actionObjects.setOnClickListener {
                scrollTo(titles, list, "Objects")
            }
            binding.actionSymbols.setOnClickListener {
                scrollTo(titles, list, "Symbols")
            }
            binding.actionFlags.setOnClickListener {
                scrollTo(titles, list, "Flags")
            }
            adapter.submitList(list)
        }
        return bottomSheetDialog
    }

    private fun scrollTo(titles: List<Title>, list: List<ListItem>, title: String) {
        val input = binding.itemInputSearch.inputSearch
        if (input.text.isNotBlank()) input.setText("")
        val item: ListItem? = titles.find { it.title == title }
        val index = list.indexOf(item)
        gridLayoutManager?.scrollToPositionWithOffset(index, 0)
        highlightIcon(title)
    }

    private fun highlightIcon(text: String) {
        val view = when (text) {
            "Smileys & People" -> binding.actionSmileys
            "Animals & Nature" -> binding.actionNature
            "Food & Drink" -> binding.actionFood
            "Activity" -> binding.actionActivity
            "Travel & Places" -> binding.actionTravel
            "Objects" -> binding.actionObjects
            "Symbols" -> binding.actionSymbols
            "Flags" -> binding.actionFlags
            else -> null
        }
        removeAllHighlights()
        view?.setColorFilter(ContextCompat.getColor(requireContext(), R.color.accent), android.graphics.PorterDuff.Mode.SRC_IN)
    }

    private fun removeAllHighlights() {
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
    }
}