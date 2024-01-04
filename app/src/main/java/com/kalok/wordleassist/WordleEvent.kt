package com.kalok.wordleassist

import com.kalok.wordleassist.models.InputAlphabet
import com.kalok.wordleassist.models.KeyType

sealed class WordleEvent {
    data class AlphabetCellClicked(val index: Int): WordleEvent()
    data class MatchingStateButtonClicked(val matchState: InputAlphabet.MatchingState): WordleEvent()
    data class KeyboardKeyClicked(val key: String, val keyType: KeyType): WordleEvent()
    object ClearButtonClicked: WordleEvent()
}
