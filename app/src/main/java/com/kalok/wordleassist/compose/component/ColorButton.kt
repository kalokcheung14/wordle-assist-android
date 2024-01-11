package com.kalok.wordleassist.compose.component

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.kalok.wordleassist.compose.LocalDimensions
import com.kalok.wordleassist.compose.LocalTypography
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.compose.color.Yellow
import com.kalok.wordleassist.models.InputAlphabet

@Composable
fun ColorButton(
    modifier: Modifier = Modifier,
    color: Color,
    matchingState: InputAlphabet.MatchingState,
    onClick: () -> Unit = {},
) {
    val isLandscape = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE
    
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.elevation(
            defaultElevation = LocalDimensions.current.buttonElevationDefault,
            pressedElevation = LocalDimensions.current.buttonElevationPressed,
        ),
        modifier = modifier.fillMaxHeight(),
        colors = ButtonDefaults.buttonColors(color)
    ) {
        // Show text when in landscape
        if (isLandscape) {
            Text(
                text = matchingState.stateName,
                style = LocalTypography.current.button,
            )
        }
    }
}

@Composable
@Preview
fun ColorButtonPreview() {
    WordleAssistTheme {
        Column {
            ColorButton(
                color = Yellow,
                matchingState = InputAlphabet.MatchingState.MISMATCH
            )
        }
    }
}