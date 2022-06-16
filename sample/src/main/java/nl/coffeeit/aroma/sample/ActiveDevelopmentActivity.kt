package nl.coffeeit.aroma.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import nl.coffeeit.aroma.bottomsheet.EmojiPicker

class ActiveDevelopmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_development)
        findViewById<ComposeView>(R.id.compose_view).apply {
            setContent {
                EmojiPicker()
            }
        }
    }
}