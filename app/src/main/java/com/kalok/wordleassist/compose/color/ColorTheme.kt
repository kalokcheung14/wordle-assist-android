package com.kalok.wordleassist.compose.color

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Black = getColor("#FF000000")
val White = getColor("#FFFFFFFF")
val Red = getColor("#C5281C")
val Gray = getColor("#818384")
val Yellow = getColor("#b59f3b")
val Green = getColor("#538d4e")
val Keyboard = getColor("#2691E6")
val KeyboardDark = getColor("#353538")
val NightThemeBlack = getColor("#1C1C1E")

fun getColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}

fun getColors(
    isLight: Boolean,
): Colors {
    val colorPalette: ColorPalette = getColorPalette(isLight)

    return if (isLight) {
        lightColors(
            primary = colorPalette.ColorPrimary,
            secondary = colorPalette.ColorSecondary,
            secondaryVariant = colorPalette.ColorSecondaryVariant,
            background = colorPalette.ColorBackground,
            error = colorPalette.ColorError,
            surface = colorPalette.ColorSurface,
            onPrimary = colorPalette.ColorOnPrimary,
            onSecondary = colorPalette.ColorOnSecondary,
            onBackground = colorPalette.ColorOnBackground,
            onSurface = colorPalette.ColorOnSurface,
            onError = colorPalette.ColorOnError,
        )
    } else {
        darkColors(
            primary = colorPalette.ColorPrimary,
            secondary = colorPalette.ColorSecondary,
            secondaryVariant = colorPalette.ColorSecondaryVariant,
            background = colorPalette.ColorBackground,
            error = colorPalette.ColorError,
            surface = colorPalette.ColorSurface,
            onPrimary = colorPalette.ColorOnPrimary,
            onSecondary = colorPalette.ColorOnSecondary,
            onBackground = colorPalette.ColorOnBackground,
            onSurface = colorPalette.ColorOnSurface,
            onError = colorPalette.ColorOnError
        )
    }
}
fun getColorPalette(
    isLight: Boolean,
): ColorPalette {
    return if (isLight) {
        LightColorPalette
    } else {
        DarkColorPalette
    }
}