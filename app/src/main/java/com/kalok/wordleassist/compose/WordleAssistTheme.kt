package com.kalok.wordleassist.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.kalok.wordleassist.compose.color.getColors

@Composable
fun WordleAssistTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = getColors(!isDarkTheme),
        content = content
    )
}