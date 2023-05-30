package com.kalok.wordleassist.utilities

interface GuessRule {
    fun addMismatchAlphabet(character: Char)

    fun addMatchAlphabet(position: Int, alphabet: Char)

    fun addMisplacedAlphabet(position: Int, character: Char)

    fun showGuessList(): ArrayList<String>

    fun clear()
}