package com.kalok.wordleassist

import com.kalok.wordleassist.utilities.DictionaryDataSource
import com.kalok.wordleassist.utilities.GuessRule
import com.kalok.wordleassist.utilities.GuessRuleImpl
import com.kalok.wordleassist.utilities.HardCodedDictionaryDataSource
import com.kalok.wordleassist.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<DictionaryDataSource> { HardCodedDictionaryDataSource() }

    factory<GuessRule> { GuessRuleImpl(get()) }

    viewModel { MainViewModel(get()) }
}