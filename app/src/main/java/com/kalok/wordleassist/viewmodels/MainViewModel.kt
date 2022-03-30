package com.kalok.wordleassist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kalok.wordleassist.models.InputAlphabet

class MainViewModel : ViewModel() {
    private val _selectedIndex = MutableLiveData<Int>()
    val selectedIndexValue: LiveData<Int>
        get() = _selectedIndex

    private val _inputAlphabets: Array<InputAlphabet?> = arrayOfNulls(25)

    init {
        _selectedIndex.value = 0
    }

    fun setSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun setAlphabetAt(index: Int, alphabet: Char) {
        if (_inputAlphabets[index] != null) {
            // If input alphabet is not null, assign the alphabet
            _inputAlphabets[index]?.alphabet = alphabet
        } else {
            // If input alphabet is null, create one and pass the alphabet
            // The default matching state will be "mismatch"
            _inputAlphabets[index] = InputAlphabet(alphabet)
        }
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
        if (_inputAlphabets[index] != null) {
            // If input alphabet is not null, assign the state
            _inputAlphabets[index]?.state = state
        } else {
            // If input alphabet is null, create one and pass the state
            // Set the default alphabet to null
            _inputAlphabets[index] = InputAlphabet(null, state)
        }
    }
}