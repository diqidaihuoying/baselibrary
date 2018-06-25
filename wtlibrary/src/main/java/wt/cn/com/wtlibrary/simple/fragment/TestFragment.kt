package wt.cn.com.wtlibrary.simple.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.widget.Toast
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import wt.cn.com.wtlibrary.R
import wt.cn.com.wtlibrary.base.BaseFragment
import wt.cn.com.wtlibrary.base.BaseRecyclerAdapter
import wt.cn.com.wtlibrary.databinding.FragmentTestBinding
import wt.cn.com.wtlibrary.http.ResponseCallback
import wt.cn.com.wtlibrary.simple.adapter.TestRecAdapter
import wt.cn.com.wtlibrary.simple.bean.WorkInfo
import wt.cn.com.wtlibrary.simple.http.RetrofitHelp
import java.util.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import wt.cn.com.wtlibrary.eventbus.Message
import wt.cn.com.wtlibrary.eventbus.MessageEvent
import wt.cn.com.wtlibrary.util.ToastUtils


/**
 * 创建日期：2018/6/11 on 16:51
 * 描述:
 * 作者:wantao
 */
class TestFragment : BaseFragment(), OnRefreshListener, OnLoadMoreListener {

    val TAG:String ="TestFragment"
    override val layoutId: Int
        get() = R.layout.fragment_test

    var dataBinding: FragmentTestBinding? = null
    var list: MutableList<WorkInfo> = mutableListOf()
    var adapter: TestRecAdapter? = null
    var pageSize: Int? = 10
    var currentPage: Int? = 0

    override fun initView() {
        dataBinding = mDataBinding as FragmentTestBinding?
        dataBinding!!.refreshLayout.setOnRefreshListener(this)
        dataBinding!!.refreshLayout.setOnLoadMoreListener(this)

        adapter = TestRecAdapter(context, list)
        dataBinding!!.recyclerView.layoutManager=LinearLayoutManager(context)
        dataBinding!!.recyclerView.adapter=adapter

        EventBus.getDefault().post(MessageEvent(Message.TEST_MESSAGE))

        dataBinding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        dataBinding!!.recyclerView.adapter = adapter
    }

    override fun initData() {
        var baseMap = RetrofitHelp.getBaseMap() as HashMap
        baseMap.put("pi", currentPage.toString())
        baseMap.put("ps", pageSize.toString())
        baseMap.put("hid", "0")
        applySchedulers(RetrofitHelp.apiService!!.getWorkList(baseMap as Map<String, Any>?)).subscribe(newObserver(object : ResponseCallback<List<WorkInfo>>() {
            override fun onNext(t: List<WorkInfo>) {
                if (currentPage == 1) {
                    list.clear()
                }
                list.addAll(t)
                adapter!!.notifyDataSetChanged()
            }
        }))

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        initData()
        dataBinding!!.refreshLayout.finishRefresh(1000, true)//传入false表示刷新失败
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        dataBinding!!.refreshLayout.finishLoadMore(1000)//传入false表示刷新失败
    }


}