package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kalok.wordleassist.WordleEvent
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.compose.clickableWithoutRipple

@Composable
fun InputTable(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onEvent: (WordleEvent) -> Unit = {},
) {
    val wordLength = 5

    Column(
        modifier = modifier
    ) {
        repeat(wordLength) { row ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            ) {
                repeat(wordLength) { column ->
                    val cellIndex = row * wordLength + column
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .padding(LocalDimensions.current.cellMargin),
                    ) {
                        AlphabetCell(
                            index = cellIndex,
                            isSelected = (cellIndex == selectedIndex),
                            modifier = Modifier.clickableWithoutRipple {
                                onEvent(WordleEvent.AlphabetCellClicked(cellIndex))
                            }
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