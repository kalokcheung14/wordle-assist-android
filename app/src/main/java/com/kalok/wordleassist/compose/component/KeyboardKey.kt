package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.LocalTypography
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.models.KeyType

@Composable
fun KeyboardKey(
    modifier: Modifier = Modifier,
    key: String,
    onClick: (String, KeyType) -> Unit = { _,_ -> },
) {
    // Determine key's handling by the length of the word on the key
    // If the key has a length of 1 then it is for input
    // otherwise it should be a functional key
    val keyType = if (key.length > 1) KeyType.FUNCTION else KeyType.INPUT

    Button(
        modifier = modifier
            .fillMaxWidth(),
        onClick = {

            onClick.invoke(key, keyType)
        },
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