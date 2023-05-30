package com.kalok.wordleassist.data

interface TreeNode<T> {
    var value: T

    fun addChild(value: T): TreeNode<T>
    fun getChildrenSet(): Set<TreeNode<T>>
    fun findChild(value: T): TreeNode<T>?
}