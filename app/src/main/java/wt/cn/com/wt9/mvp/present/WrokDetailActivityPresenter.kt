package wt.cn.com.wt9.mvp.present

import cn.com.base.http.ResponseCallback
import cn.com.base.mvp.BaseActivityPresent
import wt.cn.com.wt9.bean.WorkDetails
import wt.cn.com.wt9.http.RetrofitHelp
import wt.cn.com.wt9.mvp.contact.WorkDetailActivityContact
import java.util.HashMap

/**
 * 创建日期：2018/7/3 on 20:03
 * 描述:
 * 作者:wantao
 */
class WrokDetailActivityPresenter(var  view: WorkDetailActivityContact.IWorkDetailMvpView) : BaseActivityPresent(view),WorkDetailActivityContact.IWorkDetailPresent {
    var mWorkDetail: WorkDetails?=null


    override fun getWorkDetailData(workId: Int) {
        var hashMap = RetrofitHelp.getBaseMap() as HashMap
        hashMap.put("tid", workId.toString())
        applySchedulers(RetrofitHelp.apiService!!.getWorkDetail(hashMap as Map<String, Any>?)).subscribe(newObserver(object : ResponseCallback<WorkDetails>() {
            override fun onNext(workDetail: WorkDetails) {
                mWorkDetail=workDetail
                view.showWorkDetailView(mWorkDetail!!)
            }
        }))
    }

    override fun onFlower() {
//        if (mWorkDetail!!.mIsFavor == 0) {
//            mWorkDetail!!.mIsFavor=1
//        } else {
//            mWorkDetail!!.mIsFavor=0
//        }
    }

    override fun onCollcet() {
//        if (mWorkDetail!!.mIsFollow == 0) {
//            mWorkDetail!!.mIsFollow=1
//        } else {
//            mWorkDetail!!.mIsFollow=0
//        }
    }
}