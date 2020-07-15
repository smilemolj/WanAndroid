package com.yuefeng.base.base.view

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.yuefeng.base.R
import com.yuefeng.base.app.BaseApp
import com.yuefeng.base.base.viewmodel.BaseVM

abstract class BaseVMActivity<VM : BaseVM>(private val vmclazz: Class<VM>) :
    AppCompatActivity() {
//    protected val loading by lazy { LoadDialog(this) }
    protected val viewModel: VM by lazy {
        ViewModelProvider(this).get(vmclazz)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        //super.onCreate->先执行父类onCreate，再继续
        super.onCreate(savedInstanceState)
        //禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        BarUtils.setStatusBarLightMode(this, true)
        BarUtils.setStatusBarColor(
            this, ContextCompat.getColor(BaseApp.instance, R.color.colorAccent)
        )
    }

    fun finishact(view: View) {
        finish()
    }

}
