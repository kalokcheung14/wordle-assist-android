package com.kalok.wordleassist

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.kalok.wordleassist.databinding.ActivityMainBinding
import com.kalok.wordleassist.viewmodels.MainViewModel
import com.kalok.wordleassist.views.AlphabetCellTextView

class MainActivity : AppCompatActivity() {
    private lateinit var _viewModel: MainViewModel
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _alphabetCellTextViews: Array<AlphabetCellTextView?>
    private lateinit var _keyboardButtons: ArrayList<Button?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding.root
        setContentView(view)

        // Get view model
        _viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Organise alphabet table rows as an array
        val alphabetCellRows: Array<ConstraintLayout> = arrayOf(
            _binding.tableRow1,
            _binding.tableRow2,
            _binding.tableRow3,
            _binding.tableRow4,
            _binding.tableRow5
        )

        // Organise AlphabetCellTextView as an array
        _alphabetCellTextViews = arrayOfNulls(25)
        alphabetCellRows.forEachIndexed { i, row ->
            row.children.iterator().withIndex().forEach { view ->
                // Calculate the corresponding index from 2D to 1D
                val idx = i * 5 + view.index
                // Get the value which is the view
                val v = view.value

                // Assign the view to the array
                if (v is AlphabetCellTextView) {
                    _alphabetCellTextViews[idx] = v
                }
            }
        }

        // Organise linear layout as an array
        val keyboardLinearLayouts = arrayOf(
            _binding.keyboardRow1LinearLayout,
            _binding.keyboardRow2LinearLayout,
            _binding.keyboardRow3LinearLayout
        )

        // Set up onClick listener for keyboard buttons
        _keyboardButtons = ArrayList()
        keyboardLinearLayouts.forEachIndexed { i, layout ->
            layout.children.iterator().withIndex().forEach { view ->
                // Check only button view
                if (view.value is Button) {
                    val button = view.value as Button
                    _keyboardButtons.add(button)

                    // Set up onClick Listener
                    button.setOnClickListener {
                        // Convert button text to a string
                        val buttonText = button.text.toString()

                        // If button is not reset button
                        if (buttonText != getString(R.string.reset)) {
                            // Get the value of current selected cell index
                            _viewModel.selectedIndexValue.value.let { idx ->
                                if (idx != null) {
                                    // If selected index is not null, set the alphabet at index to the alphabet on the button
                                    _viewModel.setAlphabetAt(idx, buttonText.single())
                                    _alphabetCellTextViews[idx]?.text = buttonText.single().toString()
                                }
                            }
                        }
                    }
                }
            }
        }

        // Set the default selected cell to red text color
        _viewModel.selectedIndexValue.value?.let { idx ->
            _alphabetCellTextViews[idx]?.setTextColor(Color.RED)
        }

        // Set onClick event on every cell
        _alphabetCellTextViews.forEachIndexed { i, textView ->
            textView?.setOnClickListener { view ->
                // Change the selected index in the viewModel when a cell is clicked
                _viewModel.setSelectedIndex(i)
            }
        }

        // Observe the selected index
        _viewModel.selectedIndexValue.observe(this) { i ->
            // Change the text colors of all the cells according to the selected index
            _alphabetCellTextViews.forEachIndexed { j, textView ->
                textView?.setTextColor(
                    when (i == j) {
                        true -> {
                            Color.RED
                        }
                        false -> {
                            Color.WHITE
                        }
                    }
                )
            }
        }
    }
}