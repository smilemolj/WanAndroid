package com.yuefeng.wanandroid

import android.os.Bundle
import com.yuefeng.base.base.view.BaseVMActivity
import com.yuefeng.base.base.viewmodel.BaseVM

class MainActivity : BaseVMActivity<BaseVM>(BaseVM::class.java) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}