package wt.cn.com.wt9.fragment


import android.support.v4.app.Fragment
import cn.com.base.base.BaseFragment
import cn.com.base.base.TabAdapter
import cn.com.base.simple.bean.Interest
import cn.com.base.simple.fragment.TestFragment
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

    }

    override fun initData() {
        basePresenter!!.getVpData()
    }

    override fun showVpData(result: List<Interest>) {
        titles!!.clear()
        fragments!!.clear()
        result.forEach(
                {
                    titles!!.add(it.name)
                    fragments!!.add(TestFragment.newInstance(it.id))
                    LogUtil.e(TAG,it.name)
                }
        )
        mDataBinding!!.viewPager.adapter = tabAdapter
        mDataBinding!!.tabLayout.setupWithViewPager(mDataBinding!!.viewPager);
    }

    override fun createPresenter(): ShowFragmentPresent? {
        return ShowFragmentPresent(this)
    }



}
