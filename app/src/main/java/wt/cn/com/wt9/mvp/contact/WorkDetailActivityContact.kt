package wt.cn.com.wt9.mvp.contact

import cn.com.base.mvp.MvpView
import wt.cn.com.wt9.bean.WorkDetails

/**
 * 创建日期：2018/7/3 on 19:58
 * 描述:
 * 作者:wantao
 */
class WorkDetailActivityContact {

    interface IWorkDetailPresent
    {
        fun getWorkDetailData(workId: Int)
        fun onFlower()
        fun onCollcet()
    }
    interface IWorkDetailMvpView : MvpView
    {
        fun showWorkDetailView(workDetail: WorkDetails)
    }
}