package cn.com.base.simple.mvp

import cn.com.base.http.ResponseCallback
import cn.com.base.mvp.BaseActivityPresent
import cn.com.base.simple.bean.Interest
import cn.com.base.simple.http.RetrofitHelp

/**
 * 创建日期：2018/7/3 on 19:53
 * 描述:
 * 作者:wantao
 */
class MainPresenter(var view:MainContact.IMainMvpView) : BaseActivityPresent(view) ,MainContact.IMainPresenter{

    override fun getVpData() {
        applySchedulers(RetrofitHelp.apiService!!.getInterests(RetrofitHelp.getBaseMap())).subscribe(newObserver(object : ResponseCallback<List<Interest>>() {
            override fun onNext(result: List<Interest>) {
               view.showVpData(result)
            }


        }))
    }
}