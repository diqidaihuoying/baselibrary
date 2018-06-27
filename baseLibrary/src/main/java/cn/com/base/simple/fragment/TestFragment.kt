package cn.com.base.simple.fragment

import android.support.v7.widget.LinearLayoutManager
import cn.com.base.R
import cn.com.base.adapter.TestRecAdapter
import cn.com.base.base.BaseFragment
import cn.com.base.databinding.FragmentTestBinding
import cn.com.base.eventbus.Message
import cn.com.base.eventbus.MessageEvent
import cn.com.base.http.ResponseCallback
import cn.com.base.simple.bean.WorkInfo
import cn.com.base.simple.http.RetrofitHelp
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import org.greenrobot.eventbus.EventBus
import java.util.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import cn.com.base.simple.util.SpacesItemDecoration


/**
 * 创建日期：2018/6/11 on 16:51
 * 描述:
 * 作者:wantao
 */
class TestFragment : BaseFragment(), OnRefreshListener, OnLoadMoreListener {

    val TAG: String = "TestFragment"
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
        dataBinding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        dataBinding!!.recyclerView.adapter = adapter

        EventBus.getDefault().post(MessageEvent(Message.TEST_MESSAGE))
        initRecyclerView()
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

    /**
     * recyclerview瀑布流配置
     */
    fun initRecyclerView() {
        //设置margin
        val marginLayoutParams =dataBinding!!.recyclerView.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.topMargin = this.resources.getDimensionPixelSize(R.dimen.dp_10)
        marginLayoutParams.leftMargin = this.resources.getDimensionPixelSize(R.dimen.dp_10)
        dataBinding!!.recyclerView.setLayoutParams(marginLayoutParams)
        dataBinding!!.recyclerView.setHasFixedSize(true)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        dataBinding!!.recyclerView.setLayoutManager(staggeredGridLayoutManager)
        //        recyclerView.setNestedScrollingEnabled(false); //嵌套解决滑动不流畅问题
        //设置item边距
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        dataBinding!!.recyclerView.addItemDecoration(SpacesItemDecoration(this.resources.getDimensionPixelSize(R.dimen.dp_10)))
        dataBinding!!.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?,
                                              newState: Int) {
                super.onScrollStateChanged(recyclerView,
                        newState)
                staggeredGridLayoutManager.invalidateSpanAssignments()
            }
        }
        )
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        initData()
        dataBinding!!.refreshLayout.finishRefresh(1000, true)//传入false表示刷新失败
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        dataBinding!!.refreshLayout.finishLoadMore(1000)//传入false表示刷新失败
    }


}