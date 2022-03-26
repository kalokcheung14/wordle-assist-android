package com.kalok.wordleassist.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding

// Customer TextView for alphabet input
class AlphabetCellTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): AppCompatTextView(context, attrs, defStyleAttr) {
    init {
        applyTextStyle()
    }

    private fun applyTextStyle() {
        // Set up the style of an alphabet box
        textAlignment = TEXT_ALIGNMENT_CENTER
        setTextColor(Color.WHITE)
        setBackgroundColor(Color.GRAY)
        typeface = Typeface.DEFAULT_BOLD
        textSize = 40F
        setPadding(10)
        // Text for preview
        text = "A"
    }
}