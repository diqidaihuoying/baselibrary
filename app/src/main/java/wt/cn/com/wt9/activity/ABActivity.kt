package wt.cn.com.wt9.activity

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.base.base.BaseActivity
import cn.com.base.views.TitleBarView
import wt.cn.com.wt9.R
import wt.cn.com.wt9.databinding.ActivityAbBinding
import wt.cn.com.wt9.databinding.ImageComponent
import wt.cn.com.wt9.mvp.contact.ABActivityContact
import wt.cn.com.wt9.mvp.present.ABActivityPresent

class ABActivity : BaseActivity<ActivityAbBinding, ABActivityPresent>(), ABActivityContact.IABView {

    override fun setTestContent() {
        mDataBinding!!.tv.text="hello"

    }

    override fun createPresent(): ABActivityPresent {
       return ABActivityPresent(this)
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
