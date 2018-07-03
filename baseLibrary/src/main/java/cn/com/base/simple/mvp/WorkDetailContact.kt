package cn.com.base.simple.mvp

import cn.com.base.mvp.MvpView
import cn.com.base.simple.bean.WorkDetail

/**
 * 创建日期：2018/7/3 on 19:58
 * 描述:
 * 作者:wantao
 */
class WorkDetailContact {

    interface IWorkDetailPresent
    {
        fun getWorkDetailData(workId: Int)
        fun onFlower()
        fun onCollcet()
    }

    interface IWorkDetailMvpView : MvpView
    {
        fun showWorkDetailView(workDetail: WorkDetail)
    }
}