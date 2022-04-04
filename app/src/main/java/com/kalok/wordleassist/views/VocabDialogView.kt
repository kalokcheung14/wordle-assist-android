package com.kalok.wordleassist.views

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import com.kalok.wordleassist.databinding.DialogVocabListBinding

class VocabDialogView(parentContext: Context, private val _adapter: ArrayAdapter<String>) : AlertDialog(parentContext) {
    private lateinit var _binding: DialogVocabListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DialogVocabListBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        val vocabListView = _binding.vocabListview
        val closeButton = _binding.closeDialogButton

        closeButton.setOnClickListener {
            this.dismiss()
        }

        vocabListView.adapter = _adapter
    }
}