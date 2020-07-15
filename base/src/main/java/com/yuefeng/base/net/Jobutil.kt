package com.yuefeng.base.net

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

object Jobutil {
    fun scope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Main)
    }
}
