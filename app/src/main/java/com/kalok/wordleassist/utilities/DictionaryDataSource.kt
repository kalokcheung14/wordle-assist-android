package com.kalok.wordleassist.utilities

interface DictionaryDataSource {
    fun getDictionary(): Set<String>
}