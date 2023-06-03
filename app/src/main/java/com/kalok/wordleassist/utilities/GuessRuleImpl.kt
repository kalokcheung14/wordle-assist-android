package com.kalok.wordleassist.utilities

import com.kalok.wordleassist.data.TreeNode
import com.kalok.wordleassist.data.TreeNodeImpl
import com.kalok.wordleassist.utilities.Constant.NUM_OF_LETTERS

open class GuessRuleImpl(dictionaryDataSource: DictionaryDataSource): GuessRule {
    private val _vocabList by lazy {
        dictionaryDataSource.getDictionary()
    }

    // Convert string that contains a-z alphabets to a set
    private var _alphabetList: HashSet<Char> = "abcdefghijklmnopqrstuvwxyz".toHashSet()
    // Set to store unused alphabets
    private var _mismatchAlphabetList: HashSet<Char> = HashSet()
    // Array to store correct alphabets
    private var _matchedAlphabetList = arrayOfNulls<Char>(NUM_OF_LETTERS)
    // List to store misplaced alphabets
    private var _misplacedAlphabetList: ArrayList<HashSet<Char>> = ArrayList()

    private val rootNodeMap = hashMapOf<Char, TreeNode<Char>>()

    override fun addMismatchAlphabet(character: Char) {
        println("addMismatchAlphabet $character")
        _mismatchAlphabetList.add(character)
    }

    override fun addMatchAlphabet(position: Int, alphabet: Char) {
        println("addMatchAlphabet $alphabet")
        _matchedAlphabetList[position] = alphabet
    }

    override fun addMisplacedAlphabet(position: Int, character: Char) {
        println("addMisplacedAlphabet $character")
        val charSet = _misplacedAlphabetList[position]
        charSet.add(character)
        _misplacedAlphabetList[position] = charSet
    }

    override fun showGuessList(): ArrayList<String> {
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
                    match = letterInVocab == matchedAlphabet
                    if (match.not()) {
                        // If one alphabet does not match = it does not match at all
                        // stop checking the rest of the word
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
                allChars.addAll(_misplacedAlphabetList.flatten())

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

    override fun clear() {
        // Clear all guess criteria
        _mismatchAlphabetList = HashSet()
        _misplacedAlphabetList = ArrayList()
        _matchedAlphabetList = arrayOfNulls(NUM_OF_LETTERS)
        initList()
    }

    init {
//        constructVocabTree()
        initList()
    }

    private fun initList() {
        // Initialise alphabets array and set
        _matchedAlphabetList = arrayOfNulls(NUM_OF_LETTERS)
        repeat(NUM_OF_LETTERS) {
            _misplacedAlphabetList.add(HashSet())
        }
        println("_misplacedAlphabetList ${_misplacedAlphabetList.size}")
    }

    private fun constructVocabTree() {
        _vocabList.forEach {
            val firstChar = it.first()
            val rootNode = if (rootNodeMap.containsKey(firstChar)) {
                rootNodeMap[firstChar]
            } else {
                TreeNodeImpl(firstChar).also { node ->
                    rootNodeMap[firstChar] = node
                }
            }
            var parentNode = rootNode
            it.substring(1).forEach { char ->
                parentNode = parentNode?.addChild(char)
            }
        }
    }
}