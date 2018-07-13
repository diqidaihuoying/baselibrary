package wt.cn.com.wt9.fragment


import android.app.Activity
import android.support.v4.app.Fragment
import android.view.View
import cn.com.base.base.BaseFragment
import cn.com.base.util.LogUtil
import skin.support.annotation.Skinable
import wt.cn.com.wt9.R
import wt.cn.com.wt9.databinding.FragmentMeBinding
import wt.cn.com.wt9.mvp.present.MeFragmentPresent


/**
 * A simple [Fragment] subclass.
 *
 */
class MeFragment : BaseFragment<FragmentMeBinding, MeFragmentPresent>(),View.OnClickListener {
    companion object {
        var TAG="MeFragment"
    }
    override val layoutId: Int
        get() = R.layout.fragment_me

    override fun initView() {
        LogUtil.e(TAG,"--------initView")
        mDataBinding!!.tvSwitch.setOnClickListener (this)
    }

    override fun initData() {
    }

    override fun createPresenter(): MeFragmentPresent? {
       return MeFragmentPresent(activity as Activity,this)
    }

    override fun onClick(v: View?) {
        LogUtil.e(TAG,"--------onClick")
        when(v!!.id)
        {
            R.id.tv_switch ->
            {
                LogUtil.e(TAG,"--------basePresenter.switchM()")
                basePresenter!!.switchM()
            }
        }
    }

}
