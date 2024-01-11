package com.kalok.wordleassist.utilities

import com.kalok.wordleassist.utilities.Constant.LOG_TAG
import timber.log.Timber

object TimberLogger: Logger {
    override fun i(log: String) {
        Timber.tag(LOG_TAG).i(log)
    }
}