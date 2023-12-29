package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kalok.wordleassist.R
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.LocalTypography
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.compose.LocalColors

@Composable
fun AlphabetCell(
    modifier: Modifier = Modifier,
    index: Int = -1,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(LocalDimensions.current.cellMargin)
    ) {
        Box(
            modifier = modifier
                .background(LocalColors.current.ColorAlphabetCell)
                .height(LocalDimensions.current.cellSize)
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.wrapContentSize(),
                text = stringResource(id = R.string.placeholder),
                color = LocalColors.current.ColorAlphabetCellText,
                style = LocalTypography.current.cell,
            )
        }
    }
}

@Composable
@Preview
fun AlphabetCellPreview() {
    WordleAssistTheme {
        AlphabetCell()
    }
}