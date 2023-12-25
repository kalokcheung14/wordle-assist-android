package com.kalok.wordleassist.compose.color

import androidx.compose.ui.graphics.Color

object DarkColorPalette: ColorPalette {
    override val ColorPrimary: Color
        get() = White
    override val ColorSecondary: Color
        get() = Gray
    override val ColorBackground: Color
        get() = NightThemeBlack
    override val ColorSurface: Color
        get() = Black
    override val ColorError: Color
        get() = Error
    override val ColorOnError: Color
        get() = Error
    override val ColorOnPrimary: Color
        get() = White
    override val ColorOnSecondary: Color
        get() = Gray
    override val ColorOnSurface: Color
        get() = Black
    override val ColorOnBackground: Color
        get() = NightThemeBlack
}