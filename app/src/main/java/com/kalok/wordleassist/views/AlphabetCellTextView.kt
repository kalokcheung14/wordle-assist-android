package com.kalok.wordleassist.views

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
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
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
        setBackgroundColor(Color.parseColor("#818384"))
        typeface = Typeface.DEFAULT_BOLD
        textSize = 30F
        setPadding(10)
        // Text for preview
        text = PLACE_HOLDER
    }

    companion object {
        const val PLACE_HOLDER = "?"
    }
}