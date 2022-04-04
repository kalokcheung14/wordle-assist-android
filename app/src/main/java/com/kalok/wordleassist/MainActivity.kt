package com.kalok.wordleassist

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.kalok.wordleassist.databinding.ActivityMainBinding
import com.kalok.wordleassist.utilities.GuessRule
import com.kalok.wordleassist.viewmodels.MainViewModel
import com.kalok.wordleassist.views.AlphabetCellTextView
import com.kalok.wordleassist.views.VocabDialogView
import kotlin.math.pow

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {
    private lateinit var _viewModel: MainViewModel
    private lateinit var _binding: ActivityMainBinding
    private lateinit var _alphabetCellTextViews: Array<AlphabetCellTextView?>
    private lateinit var _keyboardButtons: ArrayList<TextView?>
    private lateinit var _guessButton: Button
    private lateinit var _clearButton: Button

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

        val numOfLetters = GuessRule.NUM_OF_LETTERS

        // Organise AlphabetCellTextView as an array
        _alphabetCellTextViews = arrayOfNulls(numOfLetters.toDouble().pow(2).toInt())
        alphabetCellRows.forEachIndexed { i, row ->
            row.children.iterator().withIndex().forEach { view ->
                // Calculate the corresponding index from 2D to 1D
                val idx = i * numOfLetters + view.index
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
        keyboardLinearLayouts.forEach { layout ->
            layout.children.iterator().withIndex().forEach { view ->
                // Check only button view
                if (view.value is TextView) {
                    val button = view.value as TextView
                    _keyboardButtons.add(button)

                    // Set up onClick Listener
                    button.setOnClickListener {
                        // Convert button text to a string
                        val buttonText = button.text.toString()

                        // Get the value of current selected cell index
                        _viewModel.selectedIndexValue.value?.let { idx ->
                            handleKeyboardButtonEvent(idx, buttonText)
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
            textView?.setOnClickListener {
                // Change the selected index in the viewModel when a cell is clicked
                _viewModel.setSelectedIndex(i)
            }
        }

        // Observe the selected index
        _viewModel.selectedIndexValue.observe(this) { i ->
            // Change the text colors of all the cells according to the selected index
            _alphabetCellTextViews.forEachIndexed { j, textView ->
                textView?.setTextColor(
                    // Change the selected cell's text color to red, other cells' text colors to white
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

        // Set up color buttons and onClick actions to change cell color and update alphabet state
        val grayView = _binding.grayView
        val yellowView = _binding.yellowView
        val greenView = _binding.greenView

        grayView.setOnClickListener {
            onClickColorButton(R.color.gray)
        }

        yellowView.setOnClickListener {
            onClickColorButton(R.color.yellow)
        }

        greenView.setOnClickListener {
            onClickColorButton(R.color.green)
        }

        // Set up on click event handling when guess button is clicked
        _guessButton = _binding.guessButton
        _guessButton.setOnClickListener {
            // Get the result list of guessing from viewModel
            val vocabList = _viewModel.guess()

            if (vocabList.isNotEmpty()) {
                // If the list is not empty, display the matched vocabs in a dialog
                val adapter = ArrayAdapter(this, R.layout.list_item, vocabList)
                val dialog = VocabDialogView(this, adapter)
                dialog.show()
            } else {
                // If the list is empty, show a toast to the user
                Toast.makeText(
                    this,
                    this.getString(R.string.no_match),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Set up on click event handling when clear button is clicked
        _clearButton = _binding.clearButton
        _clearButton.setOnClickListener {
            // Clear alphabet input
            _viewModel.clearInput()
            // Reset alphabet and color for each cell
            _alphabetCellTextViews.forEachIndexed { i, _ ->
                _alphabetCellTextViews[i]?.text = getString(R.string.placeholder)
                _alphabetCellTextViews[i]?.setBackgroundColor(getColor(R.color.gray))
            }
        }
    }

    /**
     * Reset the alphabet in a cell
     */
    private fun resetCell(idx: Int) {
        _viewModel.setAlphabetAt(idx, null)
        _alphabetCellTextViews[idx]?.text = getString(R.string.placeholder)
    }

    /**
     * Handle keyboard button click event
     */
    private fun handleKeyboardButtonEvent(idx: Int, buttonText: CharSequence) {
        // If selected index is not null
        if (buttonText == getString(R.string.reset)) {
            // If selected button is reset, set the cell at index to placeholder
            resetCell(idx)
        } else if (buttonText == getString(R.string.next)) {
            // If selected button is next,
            // set the selected index to the next index (if has next index)
            selectNextCell(idx)
        } else if (buttonText == getString(R.string.delete)) {
            // If selected button is delete, reset the current cell
            // Set the selected index to the last index (if has last index)
            resetCell(idx)
            if (idx > 0) {
                // Select last cell
                _viewModel.setSelectedIndex(idx - 1)
            }
        } else if (buttonText == getString(R.string.last)) {
            // If selected button is last,
            // set the selected index to the last index (if has last index)
            if (idx > 0) {
                // Select last cell
                _viewModel.setSelectedIndex(idx - 1)
            } else {
                // Go back to the last cell
                _viewModel.setSelectedIndex(_alphabetCellTextViews.size - 1)
            }
        } else {
            // Otherwise, set the alphabet at index to the alphabet on the button
            _viewModel.setAlphabetAt(idx, buttonText.single())
            _alphabetCellTextViews[idx]?.text = buttonText.single().toString()
            // And select the next cell automatically
            selectNextCell(idx)
        }
    }

    /**
     * Function to select next cell in the table
     */
    private fun selectNextCell(idx: Int) {
        if (idx < _alphabetCellTextViews.size - 1) {
            // Select next cell
            _viewModel.setSelectedIndex(idx + 1)
        } else {
            // Go back to the first cell
            _viewModel.setSelectedIndex(0)
        }
    }

    /**
     * Handle event of clicking color buttons
     */
    private fun onClickColorButton(colorId: Int) {
        _viewModel.selectedIndexValue.value?.let { idx ->
            // Call different set function for different color code
            // Gray -> mismatch
            // Yellow -> misplaced
            // Green -> match
            when(colorId) {
                R.color.gray -> _viewModel.setMismatchStateAt(idx)
                R.color.yellow -> _viewModel.setMisplacedStateAt(idx)
                R.color.green -> _viewModel.setMatchStateAt(idx)
            }

            // Change the background color of the selected cell
            _alphabetCellTextViews[idx]?.setBackgroundColor(getColor(colorId))
        }
    }
}