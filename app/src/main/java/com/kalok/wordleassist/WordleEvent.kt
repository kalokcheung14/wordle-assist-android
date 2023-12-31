package com.kalok.wordleassist

sealed class WordleEvent {
    data class AlphabetCellClicked(val index: Int): WordleEvent()
}
