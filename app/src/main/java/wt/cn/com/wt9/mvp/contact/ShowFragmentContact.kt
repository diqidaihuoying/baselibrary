package wt.cn.com.wt9.mvp.contact

import cn.com.base.mvp.MvpView
import cn.com.base.simple.bean.Interest

/**
 * 创建日期：2018/7/4 on 19:07
 * 描述:
 * 作者:wantao
 */
class ShowFragmentContact {

    interface IShowFragmentPresent
    {
       fun getVpData()
    }

    interface IShowFragmentMvpView:MvpView
    {
        fun showVpData(result: List<Interest>)
    }
}