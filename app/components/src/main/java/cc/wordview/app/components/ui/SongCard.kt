package cc.wordview.app.components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A composable function that displays a card representing a song with a thumbnail, track name, and artist.
 *
 * @param modifier The [Modifier] to be applied to the card for layout customization. Defaults to an empty [Modifier].
 * @param thumbnail The URL or path to the song's thumbnail image.
 * @param artist The name of the artist to be displayed.
 * @param trackName The name of the track to be displayed.
 * @param songCardPlaceholders A [SongCardPlaceholders] object providing resource IDs for error placeholders (e.g., no connection images).
 * @param onClick The callback invoked when the card is clicked, executed after a 120ms delay. Defaults to an empty lambda.
 */
@Composable
fun SongCard(
    modifier: Modifier = Modifier,
    thumbnail: String,
    artist: String,
    trackName: String,
    songCardPlaceholders: SongCardPlaceholders,
    onClick: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()

    fun onClickCard() = coroutineScope.launch {
        // delay so that the animation can be seen
        delay(120)
        onClick()
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        onClick = { onClickCard() }
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.size(120.dp)) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(-1f),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Box(
                        Modifier
                            .zIndex(-1f)
                            .alpha(0.1f)
                            .background(MaterialTheme.colorScheme.onBackground)
                    )
                    AsyncImage(
                        model = thumbnail,
                        placeholder = null,
                        error = painterResource(id = if (isSystemInDarkTheme()) songCardPlaceholders.noConnectionWhite else songCardPlaceholders.noConnectionDark),
                        contentDescription = "$trackName Cover",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillHeight,
                    )
                }
            }
            Column(
                Modifier
                    .width(120.dp)
                    .padding(top = 5.dp)
            ) {
                Text(
                    text = trackName,
                    style = typography.labelMedium,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(),
                )
                Text(
                    text = artist,
                    style = typography.labelSmall,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.inverseSurface
                )
            }
        }
    }
}