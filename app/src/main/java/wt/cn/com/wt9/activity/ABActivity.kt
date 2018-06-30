package wt.cn.com.wt9.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.base.base.BaseActivity
import cn.com.base.simple.databinding.ImageComponent
import cn.com.base.views.TitleBarView
import wt.cn.com.wt9.R
import wt.cn.com.wt9.databinding.ActivityAbBinding

class ABActivity : BaseActivity<ActivityAbBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_ab

    override fun initView() {
        mDataBinding!!.tv.text="hello"
    }

    override fun initData() {
    }

    override fun initTitle(titile: TitleBarView) {
    }

//    override fun getDatabinding(from: LayoutInflater, layoutId: Int, viewGroup: ViewGroup, b: Boolean): ActivityAbBinding? {
//        return DataBindingUtil.inflate(from,layoutId,viewGroup,b,ImageComponent())
//    }

}
