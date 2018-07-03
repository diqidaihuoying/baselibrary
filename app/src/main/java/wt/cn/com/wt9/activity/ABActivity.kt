package wt.cn.com.wt9.activity

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.base.base.BaseActivity
import cn.com.base.views.TitleBarView
import wt.cn.com.wt9.R
import wt.cn.com.wt9.databinding.ActivityAbBinding
import wt.cn.com.wt9.databinding.ImageComponent
import wt.cn.com.wt9.mvp.ABContact
import wt.cn.com.wt9.mvp.ABPresent

class ABActivity : BaseActivity<ActivityAbBinding, ABPresent>(),ABContact.IABView {

    override fun setTestContent() {
        mDataBinding!!.tv.text="hello"

    }

    override fun createPresent(): ABPresent {
       return ABPresent(this)
    }

    override val layoutId: Int
        get() = R.layout.activity_ab

    override fun initView() {

    }

    override fun initData() {
        basePresent!!.requestTestContent()
    }

    override fun initTitle(titile: TitleBarView) {
    }

    override fun getDatabinding(from: LayoutInflater, layoutId: Int, viewGroup: ViewGroup, b: Boolean): ActivityAbBinding? {
        return DataBindingUtil.inflate(from,layoutId,viewGroup,b, ImageComponent())
    }

}
