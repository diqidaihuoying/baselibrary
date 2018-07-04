package wt.cn.com.wt9.mvp.contact

import cn.com.base.mvp.MvpView

/**
 * 创建日期：2018/7/4 on 10:00
 * 描述:
 * 作者:wantao
 */
class ShowMainContact {

    interface IShowMainPresenter
    {
        fun initFragment()
        fun switchFragment(position:Int)
    }

    interface IShowMainMvpView :MvpView
    {
        fun showPublishDialog()
    }
}