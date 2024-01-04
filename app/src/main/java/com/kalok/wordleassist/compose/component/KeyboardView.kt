package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kalok.wordleassist.WordleEvent
import com.kalok.wordleassist.compose.LocalColors
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.models.InputAlphabet
import com.kalok.wordleassist.utilities.Constant

@Composable
fun KeyboardView(
    modifier: Modifier = Modifier,
    onEvent: (WordleEvent) -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colors.secondaryVariant)
    ) {
        val keyRows = Constant.KEY_ROWS

        keyRows.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = LocalDimensions.current.keyboardKeyMargin)
            ) {
                row.forEach {
                    KeyboardKey(
                        key = it,
                        modifier = Modifier
                            .weight(if (it.length > 1) 2f else 1f)
                            .padding(
                                horizontal = LocalDimensions.current.keyboardKeyMargin
                            ),
                        onClick = { key, keyType ->
                            onEvent(WordleEvent.KeyboardKeyClicked(key, keyType))
                        }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = LocalDimensions.current.keyboardKeyMargin)
                .padding(bottom = LocalDimensions.current.keyboardBottomPadding)
        ) {
            ColorButton(
                color = LocalColors.current.ColorAlphabetCell,
                matchingState = InputAlphabet.MatchingState.MISMATCH,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = LocalDimensions.current.keyboardKeyMargin),
                onClick = {
                    onEvent(WordleEvent.MatchingStateButtonClicked(InputAlphabet.MatchingState.MISMATCH))
                }
            )
            ColorButton(
                color = LocalColors.current.ColorMisplacedCell,
                matchingState = InputAlphabet.MatchingState.MISPLACED,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = LocalDimensions.current.keyboardKeyMargin),
                onClick = {
                    onEvent(WordleEvent.MatchingStateButtonClicked(InputAlphabet.MatchingState.MISPLACED))
                }
            )
            ColorButton(
                color = LocalColors.current.ColorMatchedCell,
                matchingState = InputAlphabet.MatchingState.MATCH,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = LocalDimensions.current.keyboardKeyMargin),
                onClick = {
                    onEvent(WordleEvent.MatchingStateButtonClicked(InputAlphabet.MatchingState.MATCH))
                }
            )
        }
    }
}

@Composable
@Preview
fun KeyboardViewPreview() {
    WordleAssistTheme {
        KeyboardView()
    }
}