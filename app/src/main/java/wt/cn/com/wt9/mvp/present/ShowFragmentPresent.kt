package wt.cn.com.wt9.mvp.present

import android.support.v4.app.Fragment
import cn.com.base.http.ResponseCallback
import cn.com.base.mvp.BaseFragmentPresent
import cn.com.base.util.LogUtil
import wt.cn.com.wt9.bean.Interest
import wt.cn.com.wt9.fragment.ShowChildFragment
import wt.cn.com.wt9.fragment.ShowFragment
import wt.cn.com.wt9.http.RetrofitHelp
import wt.cn.com.wt9.mvp.contact.ShowFragmentContact

/**
 * 创建日期：2018/7/4 on 19:19
 * 描述:
 * 作者:wantao
 */
class ShowFragmentPresent(var view: ShowFragmentContact.IShowFragmentMvpView) : BaseFragmentPresent(view), ShowFragmentContact.IShowFragmentPresent {

    protected var titles: MutableList<String>? = null
    protected var fragments: MutableList<Fragment>? = null

    constructor(view: ShowFragmentContact.IShowFragmentMvpView, titles: MutableList<String>?, fragments: MutableList<Fragment>?) : this(view) {
        this.titles = titles
        this.fragments = fragments
    }

    override fun getVpData() {
        view.showLoadingView()
        applySchedulers(RetrofitHelp.apiService!!.getInterests(RetrofitHelp.getBaseMap())).subscribe(newObserver(object : ResponseCallback<List<Interest>>() {
            override fun onNext(result: List<Interest>) {
                titles!!.clear()
                fragments!!.clear()
                result.forEach(
                        {
                            titles!!.add(it.name)
                            fragments!!.add(ShowChildFragment.newInstance(it.id))
                            LogUtil.e(ShowFragment.TAG, it.name)
                        }
                )
                view.showContent()
            }
        }))
    }

}
