package com.kalok.wordleassist

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.kalok.wordleassist.databinding.ActivityMainBinding
import com.kalok.wordleassist.utilities.Constant
import com.kalok.wordleassist.utilities.launchAndCollectIn
import com.kalok.wordleassist.viewmodels.MainViewModel
import com.kalok.wordleassist.views.AlphabetCellTextView
import com.kalok.wordleassist.views.VocabDialogView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.pow

@RequiresApi(Build.VERSION_CODES.M)
class MainActivity : AppCompatActivity() {
    private val _viewModel: MainViewModel by viewModel()
    private lateinit var _alphabetCellTextViews: Array<AlphabetCellTextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Organise alphabet table rows as an array
        val alphabetCellRows: Array<ConstraintLayout> = arrayOf(
            binding.tableRow1,
            binding.tableRow2,
            binding.tableRow3,
            binding.tableRow4,
            binding.tableRow5
        )

        val numOfLetters = Constant.NUM_OF_LETTERS

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
        val keyboardLinearLayouts: Array<LinearLayout> = arrayOf(
            binding.keyboardRow1LinearLayout,
            binding.keyboardRow2LinearLayout,
            binding.keyboardRow3LinearLayout
        )

        // Set up onClick listener for keyboard buttons
        val keyboardButtons: ArrayList<TextView?> = ArrayList()
        keyboardLinearLayouts.forEach { layout ->
            layout.children.iterator().withIndex().forEach { view ->
                // Check only button view
                if (view.value is TextView) {
                    val button = view.value as TextView
                    keyboardButtons.add(button)

                    // Set up onClick Listener
                    button.setOnClickListener {
                        // Convert button text to a string
                        val buttonText = button.text.toString()

                        // Get the value of current selected cell index
                        _viewModel.selectedIndexFlow.value.let { idx ->
                            handleKeyboardButtonEvent(idx, buttonText)
                        }
                    }
                }
            }
        }

        // Set the default selected cell to red text color
        _viewModel.selectedIndexFlow.value.let { idx ->
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
        _viewModel.selectedIndexFlow.launchAndCollectIn(this) { selectedIndex ->
            // Change the text colors of all the cells according to the selected index
            withContext(Dispatchers.Main) {
                _alphabetCellTextViews.forEachIndexed { textViewIndex, textView ->
                    textView?.setTextColor(
                        // Change the selected cell's text color to red, other cells' text colors to white
                        when (selectedIndex == textViewIndex) {
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

        // Set up color buttons and onClick actions to change cell color and update alphabet state
        binding.run {
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
            guessButton.setOnClickListener {
                // Get the result list of guessing from viewModel
                val vocabList = _viewModel.guess()

                if (vocabList.isNotEmpty()) {
                    // If the list is not empty, display the matched vocabs in a dialog
                    VocabDialogView(
                        this@MainActivity,
                        ArrayAdapter(this@MainActivity, R.layout.list_item, vocabList)
                    ).show()
                } else {
                    // If the list is empty, show a toast to the user
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.no_match),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // Set up on click event handling when clear button is clicked
            clearButton.setOnClickListener {
                // Clear alphabet input
                _viewModel.clearInput()
                // Reset alphabet and color for each cell
                _alphabetCellTextViews.forEach { view ->
                    view?.apply {
                        text = getString(R.string.placeholder)
                        setBackgroundColor(getColor(R.color.gray))
                    }
                }
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
        when (buttonText) {
            // If selected button is reset, set the cell at index to placeholder
            getString(R.string.reset) -> resetCell(idx)
            // If selected button is next,
            // set the selected index to the next index (if has next index)
            getString(R.string.next) -> selectNextCell(idx)
            getString(R.string.delete) -> {
                // If selected button is delete, reset the current cell
                // Set the selected index to the last index (if has last index)
                resetCell(idx)
                if (idx > 0) {
                    // Select last cell
                    _viewModel.setSelectedIndex(idx - 1)
                }
            }
            getString(R.string.last) -> {
                // If selected button is last,
                // set the selected index to the last index (if has last index)
                _viewModel.setSelectedIndex(
                    if (idx > 0) {
                        // Select last cell
                        idx - 1
                    } else {
                        // Go back to the last cell
                        _alphabetCellTextViews.size - 1
                    }
                )
            }
            else -> {
                // Otherwise, set the alphabet at index to the alphabet on the button
                _viewModel.setAlphabetAt(idx, buttonText.single())
                _alphabetCellTextViews[idx]?.text = buttonText.single().toString()
                // And select the next cell automatically
                selectNextCell(idx)
            }
        }
    }

    /**
     * Function to select next cell in the table
     */
    private fun selectNextCell(idx: Int) {
        _viewModel.setSelectedIndex(
            if (idx < _alphabetCellTextViews.size - 1) {
                // Select next cell
                idx + 1
            } else {
                // Go back to the first cell
                0
            }
        )
    }

    /**
     * Handle event of clicking color buttons
     */
    private fun onClickColorButton(colorId: Int) {
        _viewModel.selectedIndexFlow.value.let { idx ->
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