package wt.cn.com.wt9.mvp.present

import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import cn.com.base.mvp.BaseActivityPresent
import cn.com.base.util.LogUtil
import wt.cn.com.wt9.R
import wt.cn.com.wt9.fragment.ShowFragment
import wt.cn.com.wt9.mvp.contact.ShowMainContact

/**
 * 创建日期：2018/7/4 on 16:12
 * 描述:
 * 作者:wantao
 */
class ShowMainActivityPresent(var view: ShowMainContact.IShowMainMvpView) : BaseActivityPresent(view), ShowMainContact.IShowMainPresenter {

    companion object {
        val TAG="ShowMainActivityPresent"
    }
    var fragments: MutableList<Fragment>? = mutableListOf()

    override fun initFragment() {
        fragments!!.add(ShowFragment())
        fragments!!.add(ListFragment())
        fragments!!.add(ListFragment())
        fragments!!.add(ListFragment())

        switchFragment(0)
    }

    override fun switchFragment(position: Int) {
        //开启事务
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        //遍历集合
        for (i in 0 until fragments!!.size) {
            val fragment = fragments!!.get(i)
            if (i == position) {
                //显示fragment
                if (fragment.isAdded) {
                    //如果这个fragment已经被事务添加,显示
                    LogUtil.e(TAG," fragmentTransaction.show(fragment)--------"+fragment.javaClass.name)
                    fragmentTransaction.show(fragment)
                } else {
                    //如果这个fragment没有被事务添加过,添加
                    LogUtil.e(TAG," fragmentTransaction.add(R.id.container--------"+fragment.javaClass.name)
                    fragmentTransaction.add(R.id.show_container, fragment)
                }
            } else {
                //隐藏fragment
                if (fragment.isAdded) {
                    //如果这个fragment已经被事务添加,隐藏
                    fragmentTransaction.hide(fragment)
                }
            }
        }
        //提交事务
        fragmentTransaction.commit()
    }
}