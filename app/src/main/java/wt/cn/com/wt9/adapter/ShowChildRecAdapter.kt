package wt.cn.com.wt9.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.base.R
import cn.com.base.base.BaseRecyclerAdapter
import wt.cn.com.wt9.bean.Works
import wt.cn.com.wt9.databinding.ItemShowChildRecBinding
import wt.cn.com.wt9.databinding.WorkInfoComponent

/**
 * 创建日期：2018/7/5 on 11:44
 * 描述:
 * 作者:wantao
 */
class ShowChildRecAdapter(context: Context?, list: List<Works>) : BaseRecyclerAdapter<Works>(context, list) {

    override fun getlayoutId(viewType: Int): Int {
        return R.layout.item_show_child_rec
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createviewHolder(parent: ViewGroup, viewType: Int, itemShowChildBinding: ViewDataBinding): BaseRecyclerHolder {
        var testViewHolder = TestViewHolder(itemShowChildBinding.root)
        testViewHolder.itemShowChildBinding = itemShowChildBinding as ItemShowChildRecBinding
        return testViewHolder;
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        var testViewHolder = holder as TestViewHolder
        testViewHolder.itemShowChildBinding!!.workInfo = list[position]
        testViewHolder.itemShowChildBinding!!.executePendingBindings()
    }

    internal inner class TestViewHolder(outer: View) : BaseRecyclerAdapter.BaseRecyclerHolder(outer) {
        var itemShowChildBinding: ItemShowChildRecBinding? = null
    }

    override fun getDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), getlayoutId(viewType), parent, false, WorkInfoComponent())
    }
}