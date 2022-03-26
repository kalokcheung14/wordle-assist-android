package com.kalok.wordleassist

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableRow
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.kalok.wordleassist.databinding.ActivityMainBinding
import com.kalok.wordleassist.viewmodels.MainViewModel
import com.kalok.wordleassist.views.AlphabetCellTextView
import java.lang.Exception
import java.lang.annotation.AnnotationTypeMismatchException

class MainActivity : AppCompatActivity() {
    private lateinit var tableRows: Array<TableRow?>
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Get view model
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Organise table rows as an array
        tableRows = arrayOfNulls(5)
        tableRows[0] = binding.tableRow1
        tableRows[1] = binding.tableRow2
        tableRows[2] = binding.tableRow3
        tableRows[3] = binding.tableRow4
        tableRows[4] = binding.tableRow5

        getSelectedView(viewModel.getSelectedIndex()).setTextColor(Color.RED)
    }

    fun getSelectedView(index: Int): AlphabetCellTextView {
        // Calculate the row and column of the cell/view corresponds to the provided index
        // and get the cell
        val row = index / 5
        val column = 5 * row + index
        val view = tableRows[row]?.children?.elementAt(column)

        // Return the cell if it is an AlphabetCellTextView
        if (view is AlphabetCellTextView) {
            return view
        } else {
            throw Exception("Type is not AlphabetTextView")
        }
    }
}