package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kalok.wordleassist.compose.clickableWithoutRipple

@Composable
fun NoRippleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier.clickableWithoutRipple {
            onClick.invoke()
        }
    ) {
        content()
    }
}