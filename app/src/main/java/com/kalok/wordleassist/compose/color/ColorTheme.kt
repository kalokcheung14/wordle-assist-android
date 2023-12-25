package com.kalok.wordleassist.compose.color

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Purple200 = getColor("#FFBB86FC")
val Purple500 = getColor("#FF6200EE")
val Purple700 = getColor("#FF3700B3")
val Teal200 = getColor("#FF03DAC5")
val Teal700 = getColor("#FF018786")
val Black = getColor("#FF000000")
val White = getColor("#FFFFFFFF")
val Blue = getColor("#2196F3")
val Error = getColor("#C5281C")
val Gray = getColor("#818384")
val Yellow = getColor("#b59f3b")
val Green = getColor("#538d4e")
val Keyboard = getColor("#2691E6")
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