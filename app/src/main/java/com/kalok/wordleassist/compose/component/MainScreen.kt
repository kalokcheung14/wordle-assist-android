package com.kalok.wordleassist.compose.component

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kalok.wordleassist.R
import com.kalok.wordleassist.WordleEvent
import com.kalok.wordleassist.compose.*
import com.kalok.wordleassist.viewmodels.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onEvent: (WordleEvent) -> Unit = {},
) {
    val configuration = LocalConfiguration.current
    val selectedIndex = viewModel.selectedIndexFlow.collectAsState()
    var showResult by remember { mutableStateOf(false) }
    var resultList = remember { mutableStateListOf<String>() }
    val isLandscape = configuration.orientation == ORIENTATION_LANDSCAPE

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        spotColor = MaterialTheme.colors.primary
                    )
                    .statusBarsPadding(),
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
        Row(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .then(
                        if (isLandscape) {
                            Modifier
                                .weight(1f)
                        } else {
                            Modifier.fillMaxSize()
                        }
                    )
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                InputTable(
                    modifier = Modifier
                        .padding(10.dp, 0.dp, 10.dp, 0.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally)
                        .weight(5f),
                    selectedIndex = selectedIndex.value,
                    onEvent = onEvent,
                    inputAlphabets = viewModel.inputAlphabets
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 40.dp, end = 40.dp)
                        .weight(1f)
                ) {
                    Button(
                        onClick = { onEvent(WordleEvent.ClearEvent) },
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
                        onClick = {
                            resultList = viewModel.guess().map { word ->
                                word.uppercase()
                            }.toMutableStateList()
                            showResult = true
                        },
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
                if (!isLandscape) {
                    // Hide this keyboard when in landscape
                    KeyboardView(
                        modifier = Modifier.weight(3.5f),
                        onEvent = onEvent
                    )
                }
            }
            if (isLandscape) {
                // Show this keyboard when in landscape
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    KeyboardView(
                        modifier = Modifier.weight(3.5f),
                        onEvent = onEvent
                    )
                }
            }
        }
        // Show the result dialog
        if (showResult) {
            ResultDialog(
                onDismiss = { showResult = false },
                guess = resultList
            )
        }
    }
}