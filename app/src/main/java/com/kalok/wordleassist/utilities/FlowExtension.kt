package com.kalok.wordleassist.utilities

import androidx.lifecycle.*
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

inline fun <reified T> StateFlow<T>.launchAndCollectIn(
    lifecycleOwner: LifecycleOwner,
    crossinline collector: ((T) -> Unit)
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            collect {
                collector.invoke(it)
            }
        }
    }
}