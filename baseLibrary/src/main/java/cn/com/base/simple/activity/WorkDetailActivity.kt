package cn.com.base.simple.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import cn.com.base.R
import cn.com.base.base.BaseActivity
import cn.com.base.databinding.ActivityWorkDetailBinding
import cn.com.base.http.ResponseCallback
import cn.com.base.simple.bean.WorkDetail
import cn.com.base.simple.databinding.ImageComponent
import cn.com.base.simple.http.RetrofitHelp
import java.util.*

class WorkDetailActivity : BaseActivity() {

    companion object {
        var WORKID: String? = "work_id"
    }

    var workId = -1 //作品id
    var detailBinding: ActivityWorkDetailBinding? = null

    override val layoutId: Int
        get() = R.layout.activity_work_detail


    override fun initView() {
        workId = intent.getIntExtra(WORKID, workId)
        detailBinding = mDataBinding as ActivityWorkDetailBinding
    }

    override fun initData() {
        var hashMap = RetrofitHelp.getBaseMap() as HashMap
        hashMap.put("tid", workId.toString())
        applySchedulers(RetrofitHelp.apiService!!.getWorkDetail(hashMap as Map<String, Any>?)).subscribe(newObserver(object : ResponseCallback<WorkDetail>() {
            override fun onNext(workDetail: WorkDetail) {
                detailBinding!!.workDetail=workDetail
            }
        }))
    }

    override fun initTitle() {
        mActivityBinding!!.titlebar.setTitlename("作品详情")
    }

    /**
     * 图片加载
     */
    override fun getDatabinding(from: LayoutInflater, layoutId: Int, viewGroup: ViewGroup, b: Boolean): ViewDataBinding? {
        return DataBindingUtil.inflate(from,layoutId,viewGroup,b,ImageComponent())
    }

}
