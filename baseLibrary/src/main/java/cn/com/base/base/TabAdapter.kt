package cn.com.base.base

import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * 创建日期：2018/6/11 on 16:57
 * 描述:
 * 作者:wantao
 */
class TabAdapter(fm: FragmentManager, internal var fragments: MutableList<BaseFragment<ViewDataBinding>>?, internal var titles: MutableList<String>?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return  fragments!![position]
    }

    override fun getCount(): Int {
        return  fragments!!.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles!![position]
    }

}
