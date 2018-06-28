package cn.com.base.simple.activity

import cn.com.base.R
import cn.com.base.base.BaseActivity
import cn.com.base.http.ResponseCallback
import cn.com.base.simple.http.RetrofitHelp
import java.util.*

class WorkDetailActivity : BaseActivity() {

    companion object {
        var WORKID :String? ="work_id"
    }

    var workId = -1 //作品id

    override val layoutId: Int
        get() = R.layout.activity_work_detail


    override fun initView() {
       workId=intent.getIntExtra(WORKID,workId)
    }

    override fun initData() {
        var hashMap = RetrofitHelp.getBaseMap() as HashMap
        hashMap.put("tid",workId.toString())
        applySchedulers(RetrofitHelp.apiService!!.getWorkDetail(hashMap as Map<String, Any>?)).subscribe(newObserver(object :ResponseCallback<String>()
        {
            override fun onNext(t: String) {
            }

        }))
    }

    override fun initTitle() {
        mActivityBinding!!.titlebar.setTitlename("作品详情")
    }

}
