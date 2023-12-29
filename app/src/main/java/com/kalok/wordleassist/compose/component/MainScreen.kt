package com.kalok.wordleassist.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kalok.wordleassist.R
import com.kalok.wordleassist.compose.*

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        spotColor = MaterialTheme.colors.primary
                    ).statusBarsPadding(),
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = LocalTypography.current.topBarText
                        .copy(color = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            horizontal = LocalDimensions.current.topBarMargin,
                            vertical = LocalDimensions.current.topBarVerticalMargin,
                        )
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .navigationBarsPadding()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            InputTable(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
                    .weight(5f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(start = 40.dp, end = 40.dp)
                    .weight(1f)
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = LocalColors.current.ColorClearButton
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.clear),
                        style = LocalTypography.current.button.copy(
                            color = LocalColors.current.ColorAlphabetCellText,
                        )
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = LocalColors.current.ColorGuessButton
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.guess),
                        style = LocalTypography.current.button.copy(
                            color = LocalColors.current.ColorAlphabetCellText
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            KeyboardView(
                modifier = Modifier.weight(3.5f)
            )
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    WordleAssistTheme {
        MainScreen()
    }
}

@Composable
@Preview
fun MainScreenDarkPreview() {
    WordleAssistTheme(isDarkTheme = true) {
        MainScreen()
    }
}