package com.kalok.wordleassist.compose.color

import androidx.compose.ui.graphics.Color

object DarkColorPalette: ColorPalette {
    override val ColorPrimary: Color
        get() = White
    override val ColorSecondary: Color
        get() = Gray
    override val ColorSecondaryVariant: Color
        get() = KeyboardDark
    override val ColorBackground: Color
        get() = NightThemeBlack
    override val ColorSurface: Color
        get() = Black
    override val ColorError: Color
        get() = Error
    override val ColorOnError: Color
        get() = Error
    override val ColorOnPrimary: Color
        get() = Black
    override val ColorOnSecondary: Color
        get() = White
    override val ColorOnSurface: Color
        get() = White
    override val ColorOnBackground: Color
        get() = White
}