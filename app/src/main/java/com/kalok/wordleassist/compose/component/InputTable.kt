package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kalok.wordleassist.compose.WordleAssistTheme

@Composable
fun InputTable(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        repeat(5) {
            Row {
                repeat(5) {
                    AlphabetCell()
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