package com.yuefeng.wanandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yuefeng.base.base.view.BaseVMActivity
import com.yuefeng.base.base.viewmodel.BaseVM
import com.yuefeng.wanandroid.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseVMActivity<BaseVM>(BaseVM::class.java) {
    private val homeFragment by lazy { HomeFragment.newInstance("home", "首页") }
    private val publicFragment by lazy { PublicFragment.newInstance("public", "公众号") }
    private val searchFragment by lazy { SearchFragment.newInstance("search", "搜索") }
    private val projectFragment by lazy { ProjectFragment.newInstance("project", "项目分类") }
    private val meFragment by lazy { MeFragment.newInstance("me", "我的") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnve.apply {
            enableAnimation(false)
            enableShiftingMode(false)
            enableItemShiftingMode(false)
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        switchFragment(homeFragment)
                    }
                    R.id.blog -> {
                        switchFragment(publicFragment)
                    }
                    R.id.search -> {
                        switchFragment(searchFragment)
                    }
                    R.id.project -> {
                        switchFragment(projectFragment)
                    }
                    R.id.profile -> {
                        switchFragment(meFragment)
                    }
                }
                true
            }
            selectedItemId = R.id.home
        }
    }

    private fun switchFragment(f: Fragment) {
        //动态加载fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, f)
            .commitNow()
    }
}