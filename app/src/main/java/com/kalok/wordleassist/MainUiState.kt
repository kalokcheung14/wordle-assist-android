package com.kalok.wordleassist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MainUiState {
    val guessResult by lazy {
        mutableStateListOf<String>()
    }
    var isResultLoaded by mutableStateOf(false)
}