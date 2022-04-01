package com.kalok.wordleassist

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.kalok.wordleassist.databinding.ActivityMainBinding
import com.kalok.wordleassist.viewmodels.MainViewModel
import com.kalok.wordleassist.views.AlphabetCellTextView

@RequiresApi(Build.VERSION_CODES.M)
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

                        // Get the value of current selected cell index
                        _viewModel.selectedIndexValue.value?.let { idx ->
                            // If selected index is not null
                            // If button is not reset button
                            if (buttonText != getString(R.string.reset)) {
                                // Set the alphabet at index to the alphabet on the button
                                _viewModel.setAlphabetAt(idx, buttonText.single())
                                _alphabetCellTextViews[idx]?.text = buttonText.single().toString()
                            } else if (buttonText == getString(R.string.reset)) {
                                // If select index is reset, set the cell at index to placeholder
                                _viewModel.setAlphabetAt(idx, null)
                                _alphabetCellTextViews[idx]?.text = getString(R.string.placeholder)
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
    }

    private fun onClickColorButton(colorId: Int) {
        val selectedIdx = _viewModel.selectedIndexValue.value
        selectedIdx?.let { idx ->
            // Call different set function for different color code
            // Gray -> mismatch
            // Yellow -> misplaced
            // Green -> match
            when(idx) {
                R.color.gray -> _viewModel.setMismatchStateAt(idx)
                R.color.yellow -> _viewModel.setMisplacedStateAt(idx)
                R.color.green -> _viewModel.setMatchStateAt(idx)
            }

            // Change the background color of the selected cell
            _alphabetCellTextViews[idx]?.setBackgroundColor(getColor(colorId))
        }
    }
}