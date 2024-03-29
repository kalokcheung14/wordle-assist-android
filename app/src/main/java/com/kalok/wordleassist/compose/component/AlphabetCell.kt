package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kalok.wordleassist.R
import com.kalok.wordleassist.compose.*
import com.kalok.wordleassist.models.InputAlphabet

@Composable
fun AlphabetCell(
    modifier: Modifier = Modifier,
    index: Int = -1,
    isSelected: Boolean = false,
    alphabet: InputAlphabet? = null,
) {
    val input = alphabet ?: InputAlphabet('-', InputAlphabet.MatchingState.MISMATCH)

    Box(
        modifier = modifier
            .background(
                when (input.state) {
                    InputAlphabet.MatchingState.MISMATCH -> {
                        LocalColors.current.ColorAlphabetCell
                    }
                    InputAlphabet.MatchingState.MISPLACED -> {
                        LocalColors.current.ColorMisplacedCell
                    }
                    InputAlphabet.MatchingState.MATCH -> {
                        LocalColors.current.ColorMatchedCell
                    }
                }
            )
            .height(LocalDimensions.current.cellSize)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier.wrapContentSize(),
            text = input.alphabet?.toString() ?: stringResource(id = R.string.placeholder),
            color = if (isSelected) {
                LocalColors.current.ColorAlphabetCellTextSelected
            } else {
                LocalColors.current.ColorAlphabetCellText
                                            },
            style = LocalTypography.current.cell,
        )
    }
}

@Composable
@Preview
fun AlphabetCellPreview() {
    WordleAssistTheme {
        AlphabetCell()
    }
}

@Composable
@Preview
fun AlphabetCellSelectedPreview() {
    WordleAssistTheme {
        AlphabetCell(isSelected = true)
    }
}