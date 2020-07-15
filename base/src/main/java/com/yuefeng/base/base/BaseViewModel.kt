package com.yuefeng.base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    //    网络异常处理
    protected val error_msg by lazy { MutableLiveData<String>() }
    fun getErrormsg(): MutableLiveData<String> {
        return error_msg
    }

}