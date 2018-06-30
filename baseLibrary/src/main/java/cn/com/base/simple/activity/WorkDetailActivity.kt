package cn.com.base.simple.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.base.R
import cn.com.base.base.BaseActivity
import cn.com.base.databinding.ActivityWorkDetailBinding
import cn.com.base.http.ResponseCallback
import cn.com.base.simple.bean.WorkDetail
import cn.com.base.simple.databinding.ImageComponent
import cn.com.base.simple.http.RetrofitHelp
import cn.com.base.views.TitleBarView
import java.util.*

class WorkDetailActivity : BaseActivity<ActivityWorkDetailBinding>(), View.OnClickListener {

    companion object {
        var WORKID: String? = "work_id"
    }

    var workId = -1 //作品id
    var mWorkDetail: WorkDetail? = null
    override val layoutId: Int
        get() = R.layout.activity_work_detail

    override fun initView() {
        workId = intent.getIntExtra(WORKID, workId)

        mDataBinding!!.tvFlower.setOnClickListener(this)
        mDataBinding!!.tvCollection.setOnClickListener(this)
    }

    override fun initData() {
        var hashMap = RetrofitHelp.getBaseMap() as HashMap
        hashMap.put("tid", workId.toString())
        applySchedulers(RetrofitHelp.apiService!!.getWorkDetail(hashMap as Map<String, Any>?)).subscribe(newObserver(object : ResponseCallback<WorkDetail>() {
            override fun onNext(workDetail: WorkDetail) {
                mWorkDetail = workDetail;
                mDataBinding!!.workDetail = mWorkDetail
            }
        }))
    }

    override fun initTitle(titile: TitleBarView) {
       titile.setTitlename("详情")
    }

    override fun onClick(v: View?) {
        when (v) {
        //鲜花
            mDataBinding!!.tvFlower -> {
                if (mWorkDetail!!.mIsFavor == 0) {
                    mWorkDetail!!.mIsFavor=1
                } else {
                    mWorkDetail!!.mIsFavor=0
                }
            }
        //收藏
            mDataBinding!!.tvCollection -> {
                if (mWorkDetail!!.mIsFollow == 0) {
                    mWorkDetail!!.mIsFollow=1
                } else {
                    mWorkDetail!!.mIsFollow=0
                }
            }

        }

    }

    /**
     * 图片加载
     */
    override fun getDatabinding(from: LayoutInflater, layoutId: Int, viewGroup: ViewGroup, b: Boolean): ActivityWorkDetailBinding? {
        return DataBindingUtil.inflate(from, layoutId, viewGroup, b, ImageComponent())
    }

}
