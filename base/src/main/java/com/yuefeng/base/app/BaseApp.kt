package com.yuefeng.base.app

import android.app.Application
import android.content.Context
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.CrashUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

open class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        CrashUtils.init { crashInfo, e ->
            runBlocking { delay(800) }
            AppUtils.relaunchApp()
        }
        instance = this
        context = applicationContext

    }

    companion object {
        //static 代码段可以防止内存泄露
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                //                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于
                // %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }

        lateinit var instance: BaseApp
        lateinit var context: Context
    }

}