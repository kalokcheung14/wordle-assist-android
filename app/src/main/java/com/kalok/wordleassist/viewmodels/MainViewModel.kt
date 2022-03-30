package com.kalok.wordleassist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _selectedIndex = MutableLiveData<Int>()
    val selectedIndexValue: LiveData<Int>
        get() = _selectedIndex

    private val _inputAlphabets: Array<Char?> = arrayOfNulls(25)

    init {
        _selectedIndex.value = 0
    }

    fun setSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun setAlphabetAt(index: Int, alphabet: Char) {
        _inputAlphabets[index] = alphabet
    }
}