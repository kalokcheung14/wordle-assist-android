package com.kalok.wordleassist

import com.kalok.wordleassist.utilities.*
import com.kalok.wordleassist.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory<DictionaryDataSource> { HardCodedDictionaryDataSource() }

    factory<GuessRule> { GuessRuleImpl(get()) }

    single<Logger> { TimberLogger }

    viewModel { MainViewModel(get(), get()) }
}