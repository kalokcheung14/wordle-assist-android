package com.kalok.wordleassist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var selectedIndex: Int = 0

    fun getSelectedIndex(): Int {
        return selectedIndex
    }

    fun setSelectedIndex(index: Int) {
        selectedIndex = index
    }
}