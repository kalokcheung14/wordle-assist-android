package com.kalok.wordleassist.utilities

open class GuessRuleImpl(dictionaryDataSource: DictionaryDataSource): GuessRule(dictionaryDataSource) {
    override fun guess(): ArrayList<String> {
        // A list to store matched words
        val matchedWords = ArrayList<String>()

        // Loop through all the possible words
        for (vocab in vocabList) {
            // Convert the word to a set
            var vocabCharList = vocab.toHashSet()

            // Check if the word contains any unused alphabets
            // If yes, proceed to handle the next word
            vocabCharList.retainAll(mismatchAlphabetList)
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
                val matchedAlphabet = matchedAlphabetList[i]
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
                if (misplacedAlphabetList[i].contains(letterInVocab)) {
                    match = false
                    break
                }
            }
            if (match) {
                val allChars = HashSet<Char>()

                // Put all misplaced alphabets in one single set
                allChars.addAll(misplacedAlphabetList.flatten())

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
}