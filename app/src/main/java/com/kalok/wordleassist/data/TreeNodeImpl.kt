package com.kalok.wordleassist.data

class TreeNodeImpl<T>(override var value: T) : TreeNode<T> {
    private val children by lazy {
        mutableSetOf<TreeNode<T>>()
    }

    override fun addChild(value: T): TreeNode<T> {
        val existingChild = children.find {
            it.value == value
        }
        existingChild?.let {
            return existingChild
        } ?: run {
            return TreeNodeImpl(value).also {
                children.add(it)
            }
        }
    }

    override fun getChildrenSet(): Set<TreeNode<T>> {
        return children
    }

    override fun findChild(value: T): TreeNode<T>? {
        return children.find {
            it.value == value
        }
    }
}