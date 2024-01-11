package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kalok.wordleassist.WordleEvent
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.compose.clickableWithoutRipple
import com.kalok.wordleassist.models.InputAlphabet
import com.kalok.wordleassist.utilities.Constant

@Composable
fun InputTable(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    inputAlphabets: Map<Int, InputAlphabet?> = emptyMap(),
    onEvent: (WordleEvent) -> Unit = {},
) {
    val wordLength = Constant.NUM_OF_LETTERS
    val maxNumOfGuess = Constant.MAX_NUM_OF_GUESS

    Column(
        modifier = modifier
    ) {
        repeat(maxNumOfGuess) { row ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            ) {
                repeat(wordLength) { column ->
                    val cellIndex = row * wordLength + column
                    val alphabet = inputAlphabets[cellIndex]

                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .padding(LocalDimensions.current.cellMargin),
                    ) {
                        AlphabetCell(
                            index = cellIndex,
                            isSelected = (cellIndex == selectedIndex),
                            modifier = Modifier.clickableWithoutRipple {
                                onEvent(WordleEvent.SelectAlphabetEvent(cellIndex))
                            },
                            alphabet = alphabet
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun InputTablePreview() {
    WordleAssistTheme {
        InputTable()
    }
}