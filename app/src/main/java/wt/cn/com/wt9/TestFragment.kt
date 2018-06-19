package wt.cn.com.wt9

import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_test.*
import wt.cn.com.wt9.adapter.TestRecAdapter
import wt.cn.com.wt9.databinding.FragmentTestBinding
import wt.cn.com.wtlibrary.base.BaseFragment

/**
 * 创建日期：2018/6/11 on 16:51
 * 描述:
 * 作者:wantao
 */
class TestFragment : BaseFragment(), OnRefreshListener,OnLoadMoreListener {

    override val layoutId: Int
        get() = R.layout.fragment_test

    var  dataBinding : FragmentTestBinding ?= null
    var  list: MutableList<String> = mutableListOf()
    var adapter : TestRecAdapter ?= null
    override fun initView() {
        dataBinding= mDataBinding as FragmentTestBinding?

        dataBinding!!.refreshLayout.setOnRefreshListener(this)
        dataBinding!!.refreshLayout.setOnLoadMoreListener(this)

        adapter = TestRecAdapter(context,list)

    }

    override fun initData() {
        list.clear();
        list.add("http://b.hiphotos.baidu.com/image/pic/item/96dda144ad345982b391b10900f431adcbef8415.jpg")
        list.add("http://e.hiphotos.baidu.com/image/pic/item/a686c9177f3e6709b52f456437c79f3df8dc5579.jpg")

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {

    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        list.add("http://a.hiphotos.baidu.com/image/pic/item/42a98226cffc1e1792fa64ac4690f603728de9e2.jpg")
        list.add("http://h.hiphotos.baidu.com/image/pic/item/34fae6cd7b899e51a06e25944ea7d933c9950d49.jpg")
    }


}