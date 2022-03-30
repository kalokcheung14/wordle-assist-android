package com.kalok.wordleassist.models

class InputAlphabet(
        var alphabet: Char?,
        var state: MatchingState = MatchingState.MISMATCH
    ) {
    // States of matching alphabets
    enum class MatchingState {
        MISMATCH, // The word does not contain this alphabet
        MISPLACED, // The word contains this alphabet, but it is misplaced in the wrong position
        MATCH // The word contains this alphabet and it is in the correct position
    }
}