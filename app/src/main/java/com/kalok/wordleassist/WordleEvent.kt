package com.kalok.wordleassist

import com.kalok.wordleassist.models.InputAlphabet

sealed class WordleEvent {
    data class AlphabetCellClicked(val index: Int): WordleEvent()
    data class MatchingStateButtonClicked(val matchState: InputAlphabet.MatchingState): WordleEvent()
}
