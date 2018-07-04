package wt.cn.com.wt9.mvp.present

import cn.com.base.http.ResponseCallback
import cn.com.base.mvp.BaseFragmentPresent
import cn.com.base.simple.bean.Interest
import cn.com.base.simple.http.RetrofitHelp
import wt.cn.com.wt9.mvp.contact.ShowFragmentContact

/**
 * 创建日期：2018/7/4 on 19:19
 * 描述:
 * 作者:wantao
 */
class ShowFragmentPresent(var view: ShowFragmentContact.IShowFragmentMvpView) : BaseFragmentPresent(view), ShowFragmentContact.IShowFragmentPresent {

    override fun getVpData() {
        applySchedulers(RetrofitHelp.apiService!!.getInterests(RetrofitHelp.getBaseMap())).subscribe(newObserver(object : ResponseCallback<List<Interest>>() {
            override fun onNext(result: List<Interest>) {
                view.showVpData(result)
            }
        }))
    }

}
