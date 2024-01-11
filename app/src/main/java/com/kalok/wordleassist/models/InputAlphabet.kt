package com.kalok.wordleassist.models

data class InputAlphabet(
        val alphabet: Char?,
        val state: MatchingState = MatchingState.MISMATCH
    ) {
    // States of matching alphabets
    enum class MatchingState(val stateName: String) {
        MISMATCH("MISMATCH"), // The word does not contain this alphabet
        MISPLACED("MISPLACED"), // The word contains this alphabet, but it is misplaced in the wrong position
        MATCH("MATCH") // The word contains this alphabet and it is in the correct position
    }
}