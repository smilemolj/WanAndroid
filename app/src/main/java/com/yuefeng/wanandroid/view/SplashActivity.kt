package com.yuefeng.wanandroid.view

import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.yuefeng.base.base.view.BaseVMActivity
import com.yuefeng.base.base.viewmodel.BaseVM
import com.yuefeng.wanandroid.MainActivity
import com.yuefeng.wanandroid.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity

class SplashActivity : BaseVMActivity<BaseVM>(BaseVM::class.java) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //初始化动画
        initAnmi()
    }

    private fun initAnmi() {
        //透明度动画
        val alpha = AlphaAnimation(0f, 1f)
        alpha.duration = 2000 //设置动画时长
        alpha.fillAfter = true //动画运行完成保留结束时状态
        //监听动画
        alpha.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                //动画运行完进入下一个页面
                startActivity<MainActivity>()
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        splash_layout.startAnimation(alpha)
    }
}