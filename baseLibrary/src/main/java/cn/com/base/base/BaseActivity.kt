package cn.com.base.base

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.base.R
import cn.com.base.databinding.ActivityBaseBinding
import cn.com.base.mvp.BaseActivityPresent
import cn.com.base.mvp.MvpView
import cn.com.base.views.TitleBarView
import pub.devrel.easypermissions.AfterPermissionGranted

abstract class BaseActivity<B : ViewDataBinding, P : BaseActivityPresent> : AppCompatActivity(),MvpView {

    /**
     * 布局
     */
    protected abstract val layoutId: Int

    /**
     * basebinding
     */
    private var mActivityBinding: ActivityBaseBinding? = null

    /**
     * 内容binding
     */
    protected var mDataBinding: B? = null

    protected var basePresent: P? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBinding = DataBindingUtil.setContentView(this@BaseActivity, R.layout.activity_base)
        mActivityBinding!!.root!!.setFitsSystemWindows(true)
        //添加内容布局
        mDataBinding = getDatabinding(LayoutInflater.from(this), layoutId, mActivityBinding!!.root as ViewGroup, false);
        mActivityBinding!!.container!!.addView(mDataBinding!!.root)
        basePresent = createPresent()
        basePresent!!.onCreate()
        initTitle(mActivityBinding!!.titlebar);
        initView()
        initData()
    }

    /**
     * 可重写，添加component
     */
    open protected fun getDatabinding(from: LayoutInflater, layoutId: Int, viewGroup: ViewGroup, b: Boolean): B? {
        return DataBindingUtil.inflate(from, layoutId, viewGroup, b)
    }

    @AfterPermissionGranted(10001)
    private fun requiresDefaultPermission() {
      basePresent!!.requiresDefaultPermission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        basePresent!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        basePresent!!.onActivityResult(requestCode, resultCode, data)
    }

    override fun onResume() {
        super.onResume()
        basePresent!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        basePresent!!.onPause()
    }

    override fun showNoNetwork() {
        mActivityBinding!!.multiLayout.showNoNetwork()
    }

    override fun showContent() {
        mActivityBinding!!.multiLayout.showContent()
    }

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun initTitle(titile: TitleBarView)

    abstract fun createPresent(): P
}

