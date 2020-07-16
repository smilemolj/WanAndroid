package com.yuefeng.base.base.view

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.yuefeng.base.R
import com.yuefeng.base.base.viewmodel.BaseVM
import org.jetbrains.anko.toast

abstract class BaseVMActivity<VM : BaseVM>(private val vmclazz: Class<VM>) :
    AppCompatActivity() {
    protected val title_title by lazy { findViewById(R.id.title_title) ?: TextView(this) }
    protected val viewModel: VM by lazy {
        ViewModelProvider(this).get(vmclazz)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        BarUtils.setStatusBarLightMode(this, true)
        BarUtils.setNavBarVisibility(this, false)
        viewModel.getErrormsg().observe(this, error_ob)
    }

    fun backfinish(view: View) {
        finish()
    }

    protected val error_ob: Observer<String> by lazy {
        Observer<String> {
            toast(it)
        }
    }
}
