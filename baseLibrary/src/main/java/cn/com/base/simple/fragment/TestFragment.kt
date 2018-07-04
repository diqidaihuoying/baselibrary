package cn.com.base.simple.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import cn.com.base.R
import cn.com.base.adapter.TestRecAdapter
import cn.com.base.base.BaseFragment
import cn.com.base.base.BaseRecyclerAdapter
import cn.com.base.databinding.FragmentTestBinding
import cn.com.base.eventbus.Message
import cn.com.base.eventbus.MessageEvent
import cn.com.base.simple.activity.WorkDetailActivity
import cn.com.base.simple.bean.WorkInfo
import cn.com.base.simple.mvp.TestContact
import cn.com.base.simple.mvp.TestPresenter
import cn.com.base.simple.util.SpacesItemDecoration
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import org.greenrobot.eventbus.EventBus


/**
 * 创建日期：2018/6/11 on 16:51
 * 描述:
 * 作者:wantao
 */
class TestFragment : BaseFragment<FragmentTestBinding, TestPresenter>(), OnRefreshListener, OnLoadMoreListener, BaseRecyclerAdapter.OnRcyClickListener, TestContact.ITestMvpView {


    val TAG: String = "TestFragment"
    override val layoutId: Int
        get() = R.layout.fragment_test

    var list: MutableList<WorkInfo> = mutableListOf()
    var adapter: TestRecAdapter? = null

    companion object {
        fun newInstance(id: Int): TestFragment {
            var testFragment = TestFragment();
            var bundle = Bundle();
            bundle.putInt("id", id)
            testFragment.arguments = bundle
            return testFragment
        }
    }

    override fun createPresenter(): TestPresenter? {
        return TestPresenter(this, arguments!!.getInt("id"), list)
    }

    override fun initView() {
        mDataBinding!!.refreshLayout.setOnRefreshListener(this)
        mDataBinding!!.refreshLayout.setOnLoadMoreListener(this)

        adapter = TestRecAdapter(context, list)
        adapter!!.setOnRcyClickListener(this)
        mDataBinding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        mDataBinding!!.recyclerView.adapter = adapter

        EventBus.getDefault().post(MessageEvent(Message.TEST_MESSAGE))
        initRecyclerView()
        mDataBinding!!.recyclerView.adapter = adapter
    }

    override fun initData() {

    }

    override fun showVp() {
        adapter!!.notifyDataSetChanged()
    }

    /**
     * recyclerview瀑布流配置
     */
    fun initRecyclerView() {
        //设置margin
        val marginLayoutParams = mDataBinding!!.recyclerView.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.topMargin = this.resources.getDimensionPixelSize(R.dimen.dp_10)
        marginLayoutParams.leftMargin = this.resources.getDimensionPixelSize(R.dimen.dp_10)
        mDataBinding!!.recyclerView.setLayoutParams(marginLayoutParams)
        mDataBinding!!.recyclerView.setHasFixedSize(true)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mDataBinding!!.recyclerView.setLayoutManager(staggeredGridLayoutManager)
        //        recyclerView.setNestedScrollingEnabled(false); //嵌套解决滑动不流畅问题
        //设置item边距
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mDataBinding!!.recyclerView.addItemDecoration(SpacesItemDecoration(this.resources.getDimensionPixelSize(R.dimen.dp_10)))
        mDataBinding!!.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        basePresenter!!.refresh()
        mDataBinding!!.refreshLayout.finishRefresh(1000, true)//传入false表示刷新失败
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        basePresenter!!.loadMore()
        mDataBinding!!.refreshLayout.finishLoadMore(1000)//传入false表示刷新失败
    }

    override fun onRcyClick(parent: ViewGroup, viewType: Int) {
        var intent = Intent(context, WorkDetailActivity::class.java)
        intent.putExtra(WorkDetailActivity.WORKID, list[viewType].id)
        startActivity(intent)
    }

}