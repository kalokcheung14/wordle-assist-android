package com.kalok.wordleassist.views

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import com.kalok.wordleassist.databinding.DialogVocabListBinding

class VocabDialogView(parentContext: Context, private val _adapter: ArrayAdapter<String>) : AlertDialog(parentContext) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DialogVocabListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val vocabListView = binding.vocabListview
        val closeButton = binding.closeDialogButton

        closeButton.setOnClickListener {
            this.dismiss()
        }

        vocabListView.adapter = _adapter
    }
}