package wt.cn.com.wt9.activity

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.base.base.BaseActivity
import cn.com.base.views.TitleBarView
import wt.cn.com.wt9.R
import wt.cn.com.wt9.bean.WorkDetails
import wt.cn.com.wt9.databinding.ActivityWorkDetailsBinding
import wt.cn.com.wt9.databinding.ImageComponent
import wt.cn.com.wt9.mvp.contact.WorkDetailActivityContact
import wt.cn.com.wt9.mvp.present.WrokDetailActivityPresenter

class WorkDetailsActivity: BaseActivity<ActivityWorkDetailsBinding, WrokDetailActivityPresenter>(), View.OnClickListener, WorkDetailActivityContact.IWorkDetailMvpView {

    companion object {
        var WORKID: String? = "work_id"
    }

    var workId = -1 //作品id

    override val layoutId: Int
        get() = R.layout.activity_work_details

    override fun createPresent(): WrokDetailActivityPresenter {
        return WrokDetailActivityPresenter(this)
    }

    override fun initView() {
        workId = intent.getIntExtra(WORKID, workId)
        mDataBinding!!.tvFlower.setOnClickListener(this)
        mDataBinding!!.tvCollection.setOnClickListener(this)
    }

    override fun initData() {
        basePresent!!.getWorkDetailData(workId)
    }

    override fun showWorkDetailView(workDetail: WorkDetails) {
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
    override fun getDatabinding(from: LayoutInflater, layoutId: Int, viewGroup: ViewGroup, b: Boolean): ActivityWorkDetailsBinding? {
        return DataBindingUtil.inflate(from, layoutId, viewGroup, b, ImageComponent())
    }

}
