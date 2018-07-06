package wt.cn.com.wt9.mvp.present

import cn.com.base.http.ResponseCallback
import cn.com.base.mvp.BaseFragmentPresent
import wt.cn.com.wt9.bean.Works
import wt.cn.com.wt9.http.RetrofitHelp
import wt.cn.com.wt9.mvp.contact.ShowChildFragmentContact
import java.util.*

/**
 * 创建日期：2018/7/4 on 10:30
 * 描述:
 * 作者:wantao
 */
class ShowChildFragmentPresenter(var view: ShowChildFragmentContact.ITestMvpView) : BaseFragmentPresent(view), ShowChildFragmentContact.ITestPresenter {

    companion object {
        var startPage = 1
    }

    var pageSize: Int? = 10

    var currentPage: Int? = startPage

    var hid: Int? = 0
    var list: MutableList<Works>? = null

    constructor(view: ShowChildFragmentContact.ITestMvpView, hid: Int, list: MutableList<Works>) : this(view) {
        this.hid = hid
        this.list = list
    }

    override fun getData() {
        var baseMap = RetrofitHelp.getBaseMap() as HashMap
        baseMap.put("pi", currentPage.toString())
        baseMap.put("ps", pageSize.toString())
        baseMap.put("hid", hid.toString())
        applySchedulers(RetrofitHelp.apiService!!.getWorkList(baseMap as Map<String, Any>?)).subscribe(newObserver(object : ResponseCallback<List<Works>>() {
            override fun onNext(t: List<Works>) {
                if (currentPage == startPage) {
                    list!!.clear()
                }
                if (t.isEmpty() && currentPage!! > startPage) {
                    currentPage = currentPage!! - 1
                } else {
                    list!!.addAll(t)
                }
                if (list!!.size != 0) {
                    view.showContent()
                } else {
                    view.showEmpty()
                }
            }
        }))
    }

    override fun refresh() {
        currentPage = startPage
        getData()
    }

    override fun loadMore() {
        currentPage = currentPage!! + 1
        getData()
    }
}