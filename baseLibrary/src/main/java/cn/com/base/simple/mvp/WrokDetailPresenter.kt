package cn.com.base.simple.mvp

import cn.com.base.http.ResponseCallback
import cn.com.base.mvp.BasePresent
import cn.com.base.simple.bean.WorkDetail
import cn.com.base.simple.http.RetrofitHelp
import java.util.HashMap

/**
 * 创建日期：2018/7/3 on 20:03
 * 描述:
 * 作者:wantao
 */
class WrokDetailPresenter(var  view:WorkDetailContact.IWorkDetailMvpView) : BasePresent(view),WorkDetailContact.IWorkDetailPresent {
    var mWorkDetail: WorkDetail?=null


    override fun getWorkDetailData(workId: Int) {
        var hashMap = RetrofitHelp.getBaseMap() as HashMap
        hashMap.put("tid", workId.toString())
        applySchedulers(RetrofitHelp.apiService!!.getWorkDetail(hashMap as Map<String, Any>?)).subscribe(newObserver(object : ResponseCallback<WorkDetail>() {
            override fun onNext(workDetail: WorkDetail) {
                mWorkDetail=workDetail
                view.showWorkDetailView(mWorkDetail!!)
            }
        }))
    }

    override fun onFlower() {
        if (mWorkDetail!!.mIsFavor == 0) {
            mWorkDetail!!.mIsFavor=1
        } else {
            mWorkDetail!!.mIsFavor=0
        }
    }

    override fun onCollcet() {
        if (mWorkDetail!!.mIsFollow == 0) {
            mWorkDetail!!.mIsFollow=1
        } else {
            mWorkDetail!!.mIsFollow=0
        }
    }
}