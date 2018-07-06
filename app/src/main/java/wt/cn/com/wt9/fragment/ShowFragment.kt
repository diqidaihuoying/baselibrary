package wt.cn.com.wt9.fragment


import android.support.v4.app.Fragment
import cn.com.base.base.BaseFragment
import cn.com.base.base.TabAdapter
import wt.cn.com.wt9.bean.Interest
import cn.com.base.util.LogUtil
import wt.cn.com.wt9.R
import wt.cn.com.wt9.databinding.FragmentShowBinding
import wt.cn.com.wt9.mvp.contact.ShowFragmentContact
import wt.cn.com.wt9.mvp.present.ShowFragmentPresent


/**
 * A simple [Fragment] subclass.
 *
 */
class ShowFragment : BaseFragment<FragmentShowBinding, ShowFragmentPresent>(),ShowFragmentContact.IShowFragmentMvpView {
    companion object {
        val TAG="ShowFragment"
    }

    override val layoutId: Int
        get() = R.layout.fragment_show

    protected var tabAdapter: TabAdapter? = null
    protected var titles: MutableList<String>? = mutableListOf()
    protected var fragments: MutableList<Fragment>? = mutableListOf()

    override fun initView() {
        tabAdapter=TabAdapter(childFragmentManager,fragments,titles)
    }

    override fun initData() {
        basePresenter!!.getVpData()
    }

    override fun showContent() {
        super.showContent()
        mDataBinding!!.viewPager.adapter = tabAdapter
        mDataBinding!!.tabLayout.setupWithViewPager(mDataBinding!!.viewPager);
    }

    override fun createPresenter(): ShowFragmentPresent? {
        return ShowFragmentPresent(this,titles,fragments)
    }



}
