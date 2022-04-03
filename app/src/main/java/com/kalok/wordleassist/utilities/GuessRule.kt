package com.kalok.wordleassist.utilities

open class GuessRule {
    private val _wordList = Dictionary.instance.wordList

    // Convert string that contains a-z alphabets to a set
    private var _alphabetList: HashSet<Char> = "abcdefghijklmnopqrstuvwxyz".toHashSet()
    // Set to store unused alphabets
    private var _unusedAlphabetList: HashSet<Char> = HashSet()
    // Array to store correct alphabets
    private var _matchedAlphabetList: CharArray = CharArray(NUM_OF_CHAR)
    // List to store misplaced alphabets
    private var _misPlacedAlphabetList: ArrayList<HashSet<Char>> = ArrayList()

    fun addMismatchAlphabet(character: Char) {
        _unusedAlphabetList.add(character)
    }

    fun addMatchAlphabet(position: Int, alphabet: Char) {
        _matchedAlphabetList[position] = alphabet
    }

    fun addMisplacedAlphabet(position: Int, character: Char) {
        val charSet = _misPlacedAlphabetList[position]
        charSet.add(character)
        _misPlacedAlphabetList[position] = charSet
    }

    fun showGuessList(): ArrayList<String> {
        // A list to store matched words
        val matchedWords = ArrayList<String>()

        // Remove unused alphabets from the alphabet list
        _alphabetList.removeAll(_unusedAlphabetList)

        // Loop through all the possible words
        for (word in _wordList) {
            // Convert the word to a set
            var wordCharList = word.toHashSet()

            // Check if the word contains any unused alphabets
            // If yes, proceed to handle the next word
            wordCharList.retainAll(_unusedAlphabetList)
            if (wordCharList.toTypedArray().isNotEmpty()) {
                continue
            }

            // Initialise a match flag
            var match = true

            // Loop through each alphabet of the word
            for (i in word.indices) {
                val c = word[i]

                // If the alphabet at index i is not 0
                // e.g. there is an alphabet input correctly at this index
                if (_matchedAlphabetList[i] != '0') {
                    // Check if the alphabets match
                    if (c == _matchedAlphabetList[i]) {
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
                if (_misPlacedAlphabetList[i].contains(c)) {
                    match = false
                    break
                }
            }
            if (match) {
                val allChars = HashSet<Char>()

                // Put all misplaced alphabets in one single set
                for (chars in _misPlacedAlphabetList) {
                    allChars.addAll(chars)
                }

                // Convert the word to a set of alphabets
                wordCharList = word.toHashSet()

                // Check that if the word contains the misplaced alphabets
                // If not, it does not match
                for (k in allChars) {
                    if (!wordCharList.contains(k)) {
                        match = false
                        break
                    }
                }
            }

            // Add the word to the list of matched word if it is completely matched
            if (match) {
                matchedWords.add(word)
            }
        }
        return matchedWords
    }

    fun clear() {
        // Clear all guess criteria
        _unusedAlphabetList.clear()
        _misPlacedAlphabetList.clear()
        _matchedAlphabetList = CharArray(NUM_OF_CHAR)
    }

    companion object {
        const val NUM_OF_CHAR = 5
    }

    init {
        // Initialise alphabets array and set
        for (i in 0 until NUM_OF_CHAR) {
            _matchedAlphabetList[i] = '0'
            _misPlacedAlphabetList.add(HashSet())
        }
    }
}