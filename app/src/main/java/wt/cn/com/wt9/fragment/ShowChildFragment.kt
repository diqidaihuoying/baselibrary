package wt.cn.com.wt9.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import cn.com.base.base.BaseFragment
import cn.com.base.base.BaseRecyclerAdapter
import wt.cn.com.wt9.eventbus.Message
import cn.com.base.eventbus.MessageEvent
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import org.greenrobot.eventbus.EventBus

import wt.cn.com.wt9.R
import wt.cn.com.wt9.activity.WorkDetailsActivity
import wt.cn.com.wt9.adapter.ShowChildAdapter
import wt.cn.com.wt9.bean.Works
import wt.cn.com.wt9.databinding.FragmentShowChildBinding
import wt.cn.com.wt9.mvp.contact.ShowChildFragmentContact
import wt.cn.com.wt9.mvp.present.ShowChildFragmentPresenter
import wt.cn.com.wt9.util.SpacesItemDecoration


/**
 * A simple [Fragment] subclass.
 *
 */
class ShowChildFragment : BaseFragment<FragmentShowChildBinding, ShowChildFragmentPresenter>(), OnRefreshListener, OnLoadMoreListener, BaseRecyclerAdapter.OnRcyClickListener, ShowChildFragmentContact.ITestMvpView {


    val TAG: String = "TestFragment"
    override val layoutId: Int
        get() = R.layout.fragment_show_child

    var list: MutableList<Works> = mutableListOf()
    var adapter: ShowChildAdapter? = null

    companion object {
        fun newInstance(id: Int): ShowChildFragment {
            var showChildFragment = ShowChildFragment();
            var bundle = Bundle();
            bundle.putInt("id", id)
            showChildFragment.arguments = bundle
            return showChildFragment
        }
    }

    override fun createPresenter(): ShowChildFragmentPresenter? {
        return ShowChildFragmentPresenter(this, arguments!!.getInt("id"), list)
    }

    override fun initView() {
        mDataBinding!!.refreshLayout.setOnRefreshListener(this)
        mDataBinding!!.refreshLayout.setOnLoadMoreListener(this)

        adapter = ShowChildAdapter(context, list)
        adapter!!.setOnRcyClickListener(this)
        mDataBinding!!.recyclerView.layoutManager = LinearLayoutManager(context)
        mDataBinding!!.recyclerView.adapter = adapter

        EventBus.getDefault().post(MessageEvent(Message.TEST_MESSAGE))
        initRecyclerView()
        mDataBinding!!.recyclerView.adapter = adapter
    }

    override fun initData() {
        showLoadingView()
        basePresenter!!.getData()

    }

    override fun adapterNotifyRang(startPosition: Int, count: Int) {
        adapter!!.notifyItemRangeChanged(startPosition,count)
    }

    /**
     * recyclerview瀑布流配置
     */
    fun initRecyclerView() {
        //设置margin
        val marginLayoutParams = mDataBinding!!.recyclerView.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.topMargin = this.resources.getDimensionPixelSize(cn.com.base.R.dimen.dp_10)
        marginLayoutParams.leftMargin = this.resources.getDimensionPixelSize(cn.com.base.R.dimen.dp_10)
        mDataBinding!!.recyclerView.setLayoutParams(marginLayoutParams)
        mDataBinding!!.recyclerView.setHasFixedSize(true)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mDataBinding!!.recyclerView.setLayoutManager(staggeredGridLayoutManager)
        //        recyclerView.setNestedScrollingEnabled(false); //嵌套解决滑动不流畅问题
        //设置item边距
        staggeredGridLayoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mDataBinding!!.recyclerView.addItemDecoration(SpacesItemDecoration(this.resources.getDimensionPixelSize(cn.com.base.R.dimen.dp_10)))
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
        var intent = Intent(context, WorkDetailsActivity::class.java)
        intent.putExtra(WorkDetailsActivity.WORKID, list[viewType].id)
        startActivity(intent)
    }
}
