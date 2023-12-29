package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.LocalTypography
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.compose.color.Keyboard

@Composable
fun KeyboardKey(
    modifier: Modifier = Modifier,
    key: String,
) {
    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {},
        elevation = ButtonDefaults.elevation(
            defaultElevation = LocalDimensions.current.buttonElevationDefault,
            pressedElevation = LocalDimensions.current.buttonElevationPressed
        ),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = key,
                style = LocalTypography.current.keyboardKey
                    .copy(color = MaterialTheme.colors.onSecondary)
            )
        }
    }
}

@Composable
@Preview
fun KeyboardKeyPreview() {
    WordleAssistTheme {
        KeyboardKey(key = "A")
    }
}