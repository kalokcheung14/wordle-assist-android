package com.kalok.wordleassist.utilities

abstract class GuessRule(dictionaryDataSource: DictionaryDataSource) {
    protected val vocabList by lazy {
        dictionaryDataSource.getDictionary()
    }

    // Convert string that contains a-z alphabets to a set
    protected var alphabetList: HashSet<Char> = "abcdefghijklmnopqrstuvwxyz".toHashSet()
    // Set to store unused alphabets
    protected var mismatchAlphabetList: HashSet<Char> = HashSet()
    // Array to store correct alphabets
    protected var matchedAlphabetList = arrayOfNulls<Char>(Constant.NUM_OF_LETTERS)
    // List to store misplaced alphabets
    protected var misplacedAlphabetList: ArrayList<HashSet<Char>> = ArrayList()

    init {
        initList()
    }

    private fun initList() {
        // Initialise alphabets array and set
        matchedAlphabetList = arrayOfNulls(Constant.NUM_OF_LETTERS)
        repeat(Constant.NUM_OF_LETTERS) {
            misplacedAlphabetList.add(HashSet())
        }
        println("_misplacedAlphabetList ${misplacedAlphabetList.size}")
    }

    fun addMismatchAlphabet(character: Char) {
        println("addMismatchAlphabet $character")
        mismatchAlphabetList.add(character)
    }

    fun addMatchAlphabet(position: Int, alphabet: Char) {
        println("addMatchAlphabet $alphabet")
        matchedAlphabetList[position] = alphabet
    }

    fun addMisplacedAlphabet(position: Int, character: Char) {
        println("addMisplacedAlphabet $character")
        val charSet = misplacedAlphabetList[position]
        charSet.add(character)
        misplacedAlphabetList[position] = charSet
    }
    fun showGuessList(): ArrayList<String> {
        // Remove unused alphabets from the alphabet list
        alphabetList.removeAll(mismatchAlphabetList)

        return guess()
    }

    fun clear() {
        // Clear all guess criteria
        mismatchAlphabetList = HashSet()
        misplacedAlphabetList = ArrayList()
        matchedAlphabetList = arrayOfNulls(Constant.NUM_OF_LETTERS)
        initList()
    }

    protected abstract fun guess(): ArrayList<String>
}