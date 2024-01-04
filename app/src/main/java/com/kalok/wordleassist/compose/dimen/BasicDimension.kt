package com.kalok.wordleassist.compose.dimen

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BasicDimension (
    override val cellSize: Dp = 52.dp,
    override val cellMargin: Dp = 4.dp,
    override val keyboardKeyMargin: Dp = 4.dp,
    override val buttonElevationDefault: Dp = 4.dp,
    override val buttonElevationPressed: Dp = 2.dp,
    override val topBarMargin: Dp = 16.dp,
    override val topBarVerticalMargin: Dp = 4.dp,
    override val keyboardBottomPadding: Dp = 8.dp,
    override val resultWordPadding: Dp = 8.dp,
    override val dialogCornerShape: Dp = 16.dp,
    override val dialogPadding: Dp = 32.dp,
    override val dialogIconSize: Dp = 24.dp,
    override val resultWordSidePadding: Dp = 24.dp,
    override val dialogNoResultPadding: Dp = 32.dp,
): DimensionInterface