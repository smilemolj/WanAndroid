package com.yuefeng.base.base.view

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.yuefeng.base.R
import com.yuefeng.base.app.BaseApp
import com.yuefeng.base.base.viewmodel.BaseVM
import me.imid.swipebacklayout.lib.app.SwipeBackActivity
import org.jetbrains.anko.toast

abstract class SlideBaseVMActivity<VM : BaseVM>(private val vmclazz: Class<VM>) :
    SwipeBackActivity() {
    protected val viewModel: VM by lazy {
        ViewModelProvider(this).get(vmclazz)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //禁止横屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        BarUtils.setStatusBarLightMode(this, true)
        BarUtils.setStatusBarColor(
            this, ContextCompat.getColor(BaseApp.instance, R.color.colorAccent)
        )
        viewModel.getErrormsg().observe(this, error_ob)
    }


    fun finishact(view: View) {
        finish()
    }

    protected val error_ob: Observer<String> by lazy {
        Observer<String> {
            toast(it)
        }
    }
}
