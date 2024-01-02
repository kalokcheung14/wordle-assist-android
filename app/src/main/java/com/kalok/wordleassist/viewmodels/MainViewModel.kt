package com.kalok.wordleassist.viewmodels

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.kalok.wordleassist.models.InputAlphabet
import com.kalok.wordleassist.utilities.Constant.NUM_OF_LETTERS
import com.kalok.wordleassist.utilities.GuessRule
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val _guessRule: GuessRule) : ViewModel() {
    private val _selectedIndexFlow by lazy {
        MutableStateFlow(0)
    }
    val selectedIndexFlow by lazy {
        _selectedIndexFlow.asStateFlow()
    }

    val inputAlphabets by lazy {
        mutableStateMapOf<Int, InputAlphabet?>()
    }

    fun setSelectedIndex(index: Int) {
        _selectedIndexFlow.value = index
    }

    fun guess(): ArrayList<String> {
        // Pass the input from cells to the GuessRule instance
        inputAlphabets.map { entry ->
            val index = entry.key
            val inputAlphabet = entry.value
            inputAlphabet?.state?.let { state ->
                inputAlphabet.alphabet?.let { alphabet ->
                    // Convert input to lower case for comparison
                    val lowerAlphabet = alphabet.lowercaseChar()
                    // Calculate the relative position of the alphabet
                    val position = index - NUM_OF_LETTERS * (index / NUM_OF_LETTERS)
                    // Add alphabet to rule according to its state
                    when (state) {
                        InputAlphabet.MatchingState.MISPLACED -> _guessRule.addMisplacedAlphabet(position, lowerAlphabet)
                        InputAlphabet.MatchingState.MISMATCH -> _guessRule.addMismatchAlphabet(lowerAlphabet)
                        InputAlphabet.MatchingState.MATCH -> _guessRule.addMatchAlphabet(position, lowerAlphabet)
                    }
                }
            }
        }

        // Get the guess vocab list
        val guessList = _guessRule.showGuessList()

        // Clear the rule after each guess
        _guessRule.clear()

        return guessList
    }

    fun setAlphabetAt(index: Int, alphabet: Char?) {
        // If input alphabet is not null, assign the alphabet
        // If input alphabet is null, create one and pass the alphabet
        // The default matching state will be "mismatch"
        inputAlphabets[index] = inputAlphabets[index]?.copy(alphabet = alphabet) ?: InputAlphabet(alphabet)
    }

    fun setMismatchStateAt(index: Int) {
        setStateAt(index, InputAlphabet.MatchingState.MISMATCH)
    }

    fun setMatchStateAt(index: Int) {
        setStateAt(index, InputAlphabet.MatchingState.MATCH)
    }

    fun setMisplacedStateAt(index: Int) {
        setStateAt(index, InputAlphabet.MatchingState.MISPLACED)
    }

    private fun setStateAt(index: Int, state: InputAlphabet.MatchingState) {
        // If input alphabet is not null, assign the state
        // If input alphabet is null, create one and pass the state
        // Set the default alphabet to null
        inputAlphabets[index] = inputAlphabets[index]?.copy(state = state) ?: InputAlphabet(null, state)
    }

    fun clearInput() {
        // Reset input alphabet array
        inputAlphabets.clear()
        // Reset selected index value
        _selectedIndexFlow.value = 0
    }
}