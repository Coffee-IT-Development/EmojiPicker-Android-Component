package nl.coffeeit.aroma.emojipicker.presentation.ui.emoji

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import nl.coffeeit.aroma.emojipicker.R

open class BaseBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.attributes?.windowAnimations = R.style.MyDialogAnimation
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}