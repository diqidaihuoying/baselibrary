package cn.com.base.simple.mvp

import cn.com.base.mvp.MvpView
import cn.com.base.simple.bean.WorkInfo

/**
 * 创建日期：2018/7/4 on 10:25
 * 描述:
 * 作者:wantao
 */
class TestContact {

    interface ITestPresenter
    {
        fun getData();
        fun refresh()
        fun loadMore()
    }

    interface ITestMvpView :MvpView
    {
        fun showVp()
    }
}