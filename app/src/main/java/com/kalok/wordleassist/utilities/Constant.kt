package com.kalok.wordleassist.utilities

object Constant {
    const val NUM_OF_LETTERS = 5
    const val MAX_NUM_OF_GUESS = 6

    const val FUNC_DELETE = "DEL"
    const val FUNC_LAST = "LAST"
    const val FUNC_NEXT = "NEXT"
    const val FUNC_RESET = "RESET"

    val KEY_ROWS = listOf(
        listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", FUNC_DELETE),
        listOf("A", "S", "D", "F", "G", "H", "J", "K", "L", FUNC_LAST),
        listOf(FUNC_RESET, "Z", "X", "C", "V", "B", "N", "M", FUNC_NEXT),
    )
}