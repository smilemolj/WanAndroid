package com.yuefeng.base.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseVM : ViewModel() {
    //    网络异常处理
    protected val error_msg by lazy { MutableLiveData<String>() }
    fun getErrormsg(): MutableLiveData<String> {
        return error_msg
    }

}