package cn.com.base.simple.mvp

import cn.com.base.http.ResponseCallback
import cn.com.base.mvp.BaseFragmentPresent
import cn.com.base.simple.bean.WorkInfo
import cn.com.base.simple.http.RetrofitHelp
import java.util.*

/**
 * 创建日期：2018/7/4 on 10:30
 * 描述:
 * 作者:wantao
 */
class TestPresenter(var view: TestContact.ITestMvpView) : BaseFragmentPresent(view), TestContact.ITestPresenter {

    var pageSize: Int? = 10
    var currentPage: Int? = 0

    var hid:Int?=0
    var list: MutableList<WorkInfo>?=null

    constructor(view: TestContact.ITestMvpView,hid:Int,list: MutableList<WorkInfo>) : this(view) {
        this.hid=hid
        this.list=list
    }

    override fun getData() {
        var baseMap = RetrofitHelp.getBaseMap() as HashMap
        baseMap.put("pi", currentPage.toString())
        baseMap.put("ps", pageSize.toString())
        baseMap.put("hid", hid.toString())
        applySchedulers(RetrofitHelp.apiService!!.getWorkList(baseMap as Map<String, Any>?)).subscribe(newObserver(object : ResponseCallback<List<WorkInfo>>() {
            override fun onNext(t: List<WorkInfo>) {
                if (currentPage == 1) {
                    list!!.clear()
                }
                list!!.addAll(t)
                view.showVp()
            }
        }))
    }

    override fun refresh() {
        currentPage=1
        getData()
    }

    override fun loadMore() {
        currentPage=currentPage!!+1
        getData()
    }
}