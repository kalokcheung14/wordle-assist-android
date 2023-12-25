package com.kalok.wordleassist.views

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.kalok.wordleassist.R

// Customer TextView for alphabet input
class AlphabetCellTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
): AppCompatTextView(context, attrs, defStyleAttr) {
    init {
        // Set up the style of an alphabet box
        gravity = Gravity.CENTER
        setTextColor(Color.WHITE)
        setBackgroundColor(Color.parseColor("#818384"))
        typeface = Typeface.DEFAULT_BOLD
        textSize = 30F
        setPadding(10)
        // Text for preview
        text = this.context.getString(R.string.placeholder)
    }
}