package com.kalok.wordleassist

import android.app.Application
import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.kalok.wordleassist.compose.WordleAssistTheme
import com.kalok.wordleassist.compose.component.InputTable
import com.kalok.wordleassist.compose.component.MainScreen
import com.kalok.wordleassist.models.InputAlphabet
import com.kalok.wordleassist.viewmodels.MainViewModel
import org.junit.After

import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class InputInstrumentedTest: KoinTest {
    @get:Rule
    val composeRule = createComposeRule()

    private val viewModel by inject<MainViewModel>()

    private val instrumentationContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val application = instrumentationContext.applicationContext as Application

    @Before
    fun initKoin() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidLogger(Level.DEBUG)
                androidContext(application)
                modules(appModule)
            }
        }
    }

    @After
    fun stopKoinAfterTest() = stopKoin()

    @Test
    fun testKeyboardInput_inputA_atIndex0_expectA_isVisibleAtIndex0Cell() {
        composeRule.setContent {
            WordleAssistTheme {
                MainScreen(
                    viewModel = viewModel
                )
            }
        }

        // Assertion before input
        val inputTable = composeRule
            .onNodeWithTag("input_table")
        inputTable
            .onChildren()
            .assertAll(hasText("-"))

        // Update alphabets in view model
        viewModel.setAlphabetAt(0, 'a')

        // Assertion after input
        inputTable
            .onChildAt(0)
            .assert(hasText("A", ignoreCase = true))
    }
}