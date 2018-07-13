package cn.com.base.util

import android.app.Activity
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import cn.com.base.R

/**
 * 创建日期：2018/7/13 on 14:49
 * 描述:
 * 作者:wantao
 */
object StatusColorUtil
{
    /**
     * 设置沉浸式
     */
    fun setRootView(activity:Activity,color:Int) {
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity,color)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            //修改StatusBar的颜色
            val systemBarTintManager = SystemBarTintManager(activity)
            systemBarTintManager.setStatusBarTintColor(ContextCompat.getColor(activity,color))
            systemBarTintManager.isStatusBarTintEnabled = true
        }
    }
}

