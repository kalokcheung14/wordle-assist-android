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
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.MatchCondition
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.compose.color.Gray
import com.kalok.wordleassist.compose.color.Green
import com.kalok.wordleassist.compose.color.Yellow

@Composable
fun KeyboardView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colors.secondaryVariant)
    ) {
        val keyRows = listOf(
            listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "DEL"),
            listOf("A", "S", "D", "F", "G", "H", "J", "K", "L", "LAST"),
            listOf("RESET", "Z", "X", "C", "V", "B", "N", "M", "NEXT"),
        )

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
                            )
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
                color = Gray,
                matchCondition = MatchCondition.MISMATCHED,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = LocalDimensions.current.keyboardKeyMargin)
            )
            ColorButton(
                color = Yellow,
                matchCondition = MatchCondition.MISPLACED,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = LocalDimensions.current.keyboardKeyMargin)

            )
            ColorButton(
                color = Green,
                matchCondition = MatchCondition.MATCHED,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = LocalDimensions.current.keyboardKeyMargin)
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