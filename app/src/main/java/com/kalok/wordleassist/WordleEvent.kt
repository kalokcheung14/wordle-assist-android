package com.kalok.wordleassist

import com.kalok.wordleassist.models.InputAlphabet
import com.kalok.wordleassist.models.KeyType

sealed class WordleEvent {
    data class SelectAlphabetEvent(val index: Int): WordleEvent()
    data class MatchingStateUpdateEvent(val matchState: InputAlphabet.MatchingState): WordleEvent()
    data class InputEvent(val key: String, val keyType: KeyType): WordleEvent()
    object ClearEvent: WordleEvent()
    object GuessEvent: WordleEvent()
    object DismissResultEvent: WordleEvent()
}
