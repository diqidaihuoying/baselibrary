package cn.com.base.simple.mvp

import cn.com.base.mvp.BasePresent
import cn.com.base.mvp.MvpPresenter
import cn.com.base.mvp.MvpView
import cn.com.base.simple.bean.Interest

/**
 * 创建日期：2018/7/3 on 19:48
 * 描述:
 * 作者:wantao
 */
class MainContact {

    interface IMainPresenter
    {
        fun getVpData()
    }

    interface IMainMvpView :MvpView
    {
        fun showVpData(result: List<Interest>)
    }

}