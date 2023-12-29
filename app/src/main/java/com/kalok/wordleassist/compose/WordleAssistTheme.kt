package com.kalok.wordleassist.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.kalok.wordleassist.compose.color.BasicCustomColor
import com.kalok.wordleassist.compose.color.CustomColorInterface
import com.kalok.wordleassist.compose.color.getColors
import com.kalok.wordleassist.compose.dimen.BasicDimension
import com.kalok.wordleassist.compose.dimen.DimensionInterface
import com.kalok.wordleassist.compose.type.CustomTypography
import com.kalok.wordleassist.compose.type.TypographyInterface

val LocalDimensions = staticCompositionLocalOf<DimensionInterface> { BasicDimension() }
val LocalTypography = staticCompositionLocalOf<TypographyInterface> { CustomTypography() }
val LocalColors = staticCompositionLocalOf<CustomColorInterface> { BasicCustomColor() }

@Composable
fun WordleAssistTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDimensions provides BasicDimension(),
        LocalTypography provides CustomTypography(),
        LocalColors provides BasicCustomColor(),
    ) {
        MaterialTheme(
            colors = getColors(!isDarkTheme),
            content = content
        )
    }
}