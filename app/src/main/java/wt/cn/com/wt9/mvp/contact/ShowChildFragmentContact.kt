package wt.cn.com.wt9.mvp.contact

import cn.com.base.mvp.MvpView

/**
 * 创建日期：2018/7/4 on 10:25
 * 描述:
 * 作者:wantao
 */
class ShowChildFragmentContact {

    interface ITestPresenter
    {
        fun getData();
        fun refresh()
        fun loadMore()
    }

    interface ITestMvpView :MvpView
    {
        fun adapterNotifyRang(startPosition:Int,count :Int)
    }
}