package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) {
    val wordPadding = LocalDimensions.current.resultWordPadding

    Dialog(
        onDismissRequest = onDismiss
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
                if (guess.isNotEmpty()) {
                    LazyColumn(
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
                "Hello",
                "World",
                "These",
                "Words",
                "Never",
                "Match",
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