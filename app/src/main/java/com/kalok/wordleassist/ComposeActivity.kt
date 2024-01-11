package com.kalok.wordleassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.compose.component.MainScreen
import com.kalok.wordleassist.models.InputAlphabet
import com.kalok.wordleassist.models.KeyType
import com.kalok.wordleassist.utilities.Constant
import com.kalok.wordleassist.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComposeActivity : ComponentActivity() {
    private val _viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            WordleAssistTheme {
                // Change status bar and navigation bar colors
                ChangeSystemBarsTheme(lightTheme = !isSystemInDarkTheme())
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    MainScreen(viewModel = _viewModel, onEvent = { event ->
                        when (event) {
                            is WordleEvent.SelectAlphabetEvent -> {
                                _viewModel.setSelectedIndex(event.index)
                            }
                            is WordleEvent.MatchingStateUpdateEvent -> {
                                onClickColorButton(event.matchState)
                            }
                            is WordleEvent.InputEvent -> {
                                val selectedIndex = _viewModel.selectedIndexFlow.value
                                when (event.keyType) {
                                    KeyType.FUNCTION -> {
                                        // Determine function key
                                        when (event.key) {
                                            Constant.FUNC_RESET -> {
                                                _viewModel.resetCellAt(
                                                    index = selectedIndex,
                                                )
                                            }
                                            Constant.FUNC_DELETE -> {
                                                _viewModel.deleteCellContent(selectedIndex)
                                            }
                                            Constant.FUNC_NEXT -> {
                                                _viewModel.selectNextCell(selectedIndex)
                                            }
                                            Constant.FUNC_LAST -> {
                                                _viewModel.selectPreviousCell(selectedIndex)
                                            }
                                        }
                                    }
                                    KeyType.INPUT -> {
                                        // Set selected cell input
                                        _viewModel.setAlphabetAt(
                                            index = selectedIndex,
                                            alphabet = event.key.first()
                                        )
                                        _viewModel.selectNextCell(selectedIndex)
                                    }
                                }
                            }
                            is WordleEvent.ClearEvent -> {
                                _viewModel.clearInput()
                            }
                        }
                    })
                }
            }
        }
    }

    @Composable
    private fun ChangeSystemBarsTheme(lightTheme: Boolean) {
        val barColor = MaterialTheme.colors.secondaryVariant.toArgb()
        LaunchedEffect(lightTheme) {
            if (lightTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        barColor,
                        barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.light(
                        barColor,
                        barColor,
                    )
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                )
            }
        }
    }

    /**
     * Handle event of clicking color buttons
     */
    private fun onClickColorButton(matchingState: InputAlphabet.MatchingState) {
        _viewModel.selectedIndexFlow.value.let { selectedIndex ->
            when(matchingState) {
                InputAlphabet.MatchingState.MISMATCH -> {
                    _viewModel.setMismatchStateAt(selectedIndex)
                }
                InputAlphabet.MatchingState.MISPLACED -> {
                    _viewModel.setMisplacedStateAt(selectedIndex)
                }
                InputAlphabet.MatchingState.MATCH -> {
                    _viewModel.setMatchStateAt(selectedIndex)
                }
            }
        }
    }
}