package cn.com.base.simple.activity

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.base.R
import cn.com.base.base.BaseActivity
import cn.com.base.databinding.ActivityWorkDetailBinding
import cn.com.base.simple.bean.WorkDetail
import cn.com.base.simple.databinding.ImageComponent
import cn.com.base.simple.mvp.WorkDetailContact
import cn.com.base.simple.mvp.WrokDetailPresenter
import cn.com.base.views.TitleBarView

class WorkDetailActivity : BaseActivity<ActivityWorkDetailBinding, WrokDetailPresenter>(), View.OnClickListener,WorkDetailContact.IWorkDetailMvpView {

    companion object {
        var WORKID: String? = "work_id"
    }

    var workId = -1 //作品id

    override val layoutId: Int
        get() = R.layout.activity_work_detail

    override fun createPresent(): WrokDetailPresenter {
        return WrokDetailPresenter(this)
    }

    override fun initView() {
        workId = intent.getIntExtra(WORKID, workId)
        mDataBinding!!.tvFlower.setOnClickListener(this)
        mDataBinding!!.tvCollection.setOnClickListener(this)
    }

    override fun initData() {
       basePresent!!.getWorkDetailData(workId)
    }

    override fun showWorkDetailView(workDetail: WorkDetail) {
        mDataBinding!!.workDetail = workDetail
    }

    override fun initTitle(titile: TitleBarView) {
       titile.setTitlename("详情")
    }

    override fun onClick(v: View?) {
        when (v) {
        //鲜花
            mDataBinding!!.tvFlower -> {
              basePresent!!.onFlower()
            }
        //收藏
            mDataBinding!!.tvCollection -> {
             basePresent!!.onCollcet()
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
