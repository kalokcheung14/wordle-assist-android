package com.kalok.wordleassist.compose.color

import androidx.compose.ui.graphics.Color

data class BasicCustomColor(
    override val ColorClearButton: Color = Gray,
    override val ColorGuessButton: Color = Green,
    override val ColorAlphabetCellText: Color = White,
    override val ColorAlphabetCell: Color = Gray,
): CustomColorInterface