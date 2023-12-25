package com.kalok.wordleassist.utilities

import com.kalok.wordleassist.data.TreeNode
import com.kalok.wordleassist.data.TreeNodeImpl

class TreeGuessRuleImpl(dictionaryDataSource: DictionaryDataSource): GuessRule(dictionaryDataSource) {
    private val rootNodeMap = hashMapOf<Char, TreeNode<Char>>()

    init {
        constructVocabTree()
    }

    private fun constructVocabTree() {
        vocabList.forEach {
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

        rootNodeMap.entries.forEach {
            println("TREE: ROOT")
            it.value.traverse()
        }
    }

    override fun guess(): ArrayList<String> {
        TODO("Not yet implemented")
    }
}