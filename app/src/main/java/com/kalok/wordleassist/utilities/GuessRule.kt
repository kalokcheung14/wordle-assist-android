package com.kalok.wordleassist.utilities

import javax.inject.Inject

open class GuessRule @Inject constructor(dictionary: Dictionary) {
    private val _vocabList = dictionary.vocabList

    // Convert string that contains a-z alphabets to a set
    private var _alphabetList: HashSet<Char> = "abcdefghijklmnopqrstuvwxyz".toHashSet()
    // Set to store unused alphabets
    private var _mismatchAlphabetList: HashSet<Char> = HashSet()
    // Array to store correct alphabets
    private var _matchedAlphabetList = arrayOfNulls<Char>(NUM_OF_LETTERS)
    // List to store misplaced alphabets
    private var _misplacedAlphabetList: ArrayList<HashSet<Char>> = ArrayList()

    fun addMismatchAlphabet(character: Char) {
        _mismatchAlphabetList.add(character)
    }

    fun addMatchAlphabet(position: Int, alphabet: Char) {
        _matchedAlphabetList[position] = alphabet
    }

    fun addMisplacedAlphabet(position: Int, character: Char) {
        val charSet = _misplacedAlphabetList[position]
        charSet.add(character)
        _misplacedAlphabetList[position] = charSet
    }

    fun showGuessList(): ArrayList<String> {
        // A list to store matched words
        val matchedWords = ArrayList<String>()

        // Remove unused alphabets from the alphabet list
        _alphabetList.removeAll(_mismatchAlphabetList)

        // Loop through all the possible words
        for (vocab in _vocabList) {
            // Convert the word to a set
            var vocabCharList = vocab.toHashSet()

            // Check if the word contains any unused alphabets
            // If yes, proceed to handle the next word
            vocabCharList.retainAll(_mismatchAlphabetList)
            if (vocabCharList.toTypedArray().isNotEmpty()) {
                continue
            }

            // Initialise a match flag
            var match = true

            // Loop through each alphabet of the word
            for (i in vocab.indices) {
                val letterInVocab = vocab[i]

                // If the alphabet at index i is not null
                // e.g. there is an alphabet input correctly at this index
                val matchedAlphabet = _matchedAlphabetList[i]
                if (matchedAlphabet != null) {
                    // Check if the alphabets match
                    if (letterInVocab == matchedAlphabet) {
                        match = true
                    } else {
                        // If one alphabet does not match = it does not match at all
                        // stop checking the rest of the word
                        match = false
                        break
                    }
                }

                // If an alphabet not in this position is in this position
                // e.g. this word is not a match
                // so stop checking the rest of the word
                if (_misplacedAlphabetList[i].contains(letterInVocab)) {
                    match = false
                    break
                }
            }
            if (match) {
                val allChars = HashSet<Char>()

                // Put all misplaced alphabets in one single set
                for (chars in _misplacedAlphabetList) {
                    allChars.addAll(chars)
                }

                // Convert the word to a set of alphabets
                vocabCharList = vocab.toHashSet()

                // Check that if the word contains the misplaced alphabets
                // If not, it does not match
                for (k in allChars) {
                    if (!vocabCharList.contains(k)) {
                        match = false
                        break
                    }
                }
            }

            // Add the word to the list of matched word if it is completely matched
            if (match) {
                matchedWords.add(vocab)
            }
        }
        return matchedWords
    }

    fun clear() {
        // Clear all guess criteria
        _mismatchAlphabetList = HashSet()
        _misplacedAlphabetList = ArrayList()
        _matchedAlphabetList = arrayOfNulls(NUM_OF_LETTERS)
        initList()
    }

    companion object {
        const val NUM_OF_LETTERS = 5
    }

    init {
        initList()
    }

    private fun initList() {
        // Initialise alphabets array and set
        for (i in 0 until NUM_OF_LETTERS) {
            _matchedAlphabetList[i] = null
            _misplacedAlphabetList.add(HashSet())
        }
    }
}