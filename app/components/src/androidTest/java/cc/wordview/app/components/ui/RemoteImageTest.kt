package cc.wordview.app.components.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import cc.wordview.app.components.R
import org.junit.Test

class RemoteImageTest : ComposeTest() {
    @Test
    fun renders() {
        composeTestRule.setContent {
            RemoteImage(
                model = R.drawable.nonet,
                asyncImagePlaceholders = AsyncImagePlaceholders(
                    noConnectionWhite = R.drawable.nonet,
                    noConnectionDark = R.drawable.nonet_dark
                ),
            )
        }

        composeTestRule.onNodeWithTag("remote-image")
            .assertIsDisplayed()
            .assertExists()
    }
}