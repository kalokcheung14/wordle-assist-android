package com.kalok.wordleassist.compose.dimen

import androidx.compose.ui.unit.Dp

interface DimensionInterface {
    val cellSize: Dp
    val cellMargin: Dp
    val keyboardKeyMargin: Dp
    val buttonElevationDefault: Dp
    val buttonElevationPressed: Dp
    val topBarMargin: Dp
    val topBarVerticalMargin: Dp
    val keyboardBottomPadding: Dp
    val resultWordPadding: Dp
    val resultWordSidePadding: Dp
    val dialogCornerShape: Dp
    val dialogPadding: Dp
    val dialogIconSize: Dp
    val dialogNoResultPadding: Dp
}