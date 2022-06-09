package nl.coffeeit.aroma.bottomsheet


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope

@Composable
@ExperimentalMaterialApi
fun BottomSheetWithContent(
    activityContentScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    sheetContent: @Composable () -> Unit,
    cornerShape: RoundedCornerShape,
    scrimColor: Color = Color(0x60000000)
) {

    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = cornerShape,
        scrimColor = scrimColor,
        sheetContent = {
            sheetContent()
        }
    ) {
        activityContentScope(state, scope)
    }

}