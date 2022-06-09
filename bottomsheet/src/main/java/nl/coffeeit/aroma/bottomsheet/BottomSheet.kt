package nl.coffeeit.aroma.bottomsheet


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun BottomSheetWithContent(
    activityContentScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    sheetContent: @Composable () -> Unit,
    cornerShape: RoundedCornerShape,
    scrimColor: Color = Color(0x60000000),
    hasCloseButton: Boolean = true
) {

    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = cornerShape,
        scrimColor = scrimColor,
        sheetContent = {
            if (hasCloseButton) {
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(16.dp)
                        .clickable {
                            scope.launch { state.hide() }
                        },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Close button"
                    )
                }
            }
            sheetContent()
        }
    ) {
        activityContentScope(state, scope)
    }

}