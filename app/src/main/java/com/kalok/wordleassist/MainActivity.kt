package com.kalok.wordleassist

import android.graphics.Color
import android.os.Bundle
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.kalok.wordleassist.databinding.ActivityMainBinding
import com.kalok.wordleassist.viewmodels.MainViewModel
import com.kalok.wordleassist.views.AlphabetCellTextView

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var alphabetCellTextViews: Array<AlphabetCellTextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Get view model
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Organise table rows as an array
        val tableRows: Array<TableRow?> = arrayOfNulls(5)
        tableRows[0] = binding.tableRow1
        tableRows[1] = binding.tableRow2
        tableRows[2] = binding.tableRow3
        tableRows[3] = binding.tableRow4
        tableRows[4] = binding.tableRow5

        // Organise AlphabetCellTextView as an array
        alphabetCellTextViews = arrayOfNulls(25)
        tableRows.forEachIndexed { i, row ->
            row?.children?.iterator()?.withIndex()?.forEach { view ->
                // Calculate the corresponding index from 2D to 1D
                val idx = i * 5 + view.index
                // Get the value which is the view
                val v = view.value

                // Assign the view to the array
                if (v is AlphabetCellTextView) {
                    alphabetCellTextViews[idx] = v
                }
            }
        }

        // Set the default selected cell to red text color
        alphabetCellTextViews[viewModel.getSelectedIndex()]?.setTextColor(Color.RED)

        // Set onClick event on every cell
        alphabetCellTextViews.forEachIndexed { i, textView ->
            textView?.setOnClickListener { view ->
                // Change the selected index in the viewModel when a cell is clicked
                viewModel.setSelectedIndex(i)

                // Change the text colors of all the cells according to the selected index
                alphabetCellTextViews.forEachIndexed { j, textView ->
                    textView?.setTextColor(when(i == j) {
                        true -> { Color.RED }
                        false -> { Color.WHITE }
                    })
                }
            }
        }
    }
}