package com.kalok.wordleassist.utilities

interface DictionaryDataSource {
    fun getDictionary(): HashSet<String>
}