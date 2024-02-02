package com.kalok.wordleassist.compose.component

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.LocalTypography
import com.kalok.wordleassist.compose.WordleAssistTheme

@Composable
fun ResultDialog(
    onDismiss: () -> Unit,
    guess: List<String>,
    isResultLoaded: Boolean = true,
) {
    val wordPadding = LocalDimensions.current.resultWordPadding
    val isLandscape = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalDimensions.current.dialogPadding),
            shape = RoundedCornerShape(LocalDimensions.current.dialogCornerShape),
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            top = wordPadding,
                            bottom = wordPadding,
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "GUESS RESULT",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        style = LocalTypography.current.resultTitleText,
                        textAlign = TextAlign.Center,
                    )
                    IconButton(
                        modifier = Modifier
                            .padding(
                                start = wordPadding
                            )
                            .width(LocalDimensions.current.dialogIconSize)
                            .height(IntrinsicSize.Min),
                        onClick = onDismiss
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }
                Divider()
                if (!isResultLoaded) {
                    // Display progress bar when loading
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .wrapContentHeight()
                                .padding(wordPadding)
                        )
                    }
                } else if (guess.isNotEmpty()) {
                    LazyVerticalGrid(
                        // Determine grid size base on orientation
                        columns = GridCells.Fixed(
                            if (isLandscape) {
                                3
                            } else {
                                1
                            }
                        ),
                        modifier = Modifier
                            .padding(
                                bottom = wordPadding
                            )
                    ) {
                        items(guess) { word ->
                            Text(
                                text = word,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = LocalDimensions.current.resultWordSidePadding,
                                        vertical = wordPadding
                                    )
                                    .wrapContentHeight()
                            )
                        }
                    }
                } else {
                    Text(
                        text = "No Result",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(
                                vertical = LocalDimensions.current.dialogNoResultPadding,
                            ),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ResultDialogPreview() {
    WordleAssistTheme {
        ResultDialog(
            onDismiss = {},
            guess = listOf(
                "HELLO",
                "WORLD",
                "THESE",
                "WORDS",
                "NEVER",
                "MATCH",
            )
        )
    }
}

@Composable
@Preview
fun ResultDialogEmptyPreview() {
    WordleAssistTheme {
        ResultDialog(
            onDismiss = {},
            guess = listOf()
        )
    }
}

@Composable
@Preview
fun ResultDialogLoadingPreview() {
    WordleAssistTheme {
        ResultDialog(
            onDismiss = {},
            guess = listOf(),
            isResultLoaded = false,
        )
    }
}