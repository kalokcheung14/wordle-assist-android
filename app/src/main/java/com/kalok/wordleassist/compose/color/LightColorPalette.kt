package com.kalok.wordleassist.compose.color

import androidx.compose.ui.graphics.Color

object LightColorPalette: ColorPalette {
    override val ColorPrimary: Color
        get() = Black
    override val ColorSecondary: Color
        get() = Gray
    override val ColorSecondaryVariant: Color
        get() = Keyboard
    override val ColorBackground: Color
        get() = White
    override val ColorSurface: Color
        get() = White
    override val ColorError: Color
        get() = Red
    override val ColorOnError: Color
        get() = Red
    override val ColorOnPrimary: Color
        get() = White
    override val ColorOnSecondary: Color
        get() = White
    override val ColorOnSurface: Color
        get() = Black
    override val ColorOnBackground: Color
        get() = Black
}