package cn.com.base.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.base.R
import cn.com.base.base.BaseRecyclerAdapter
import cn.com.base.databinding.ItemTestBinding
import cn.com.base.simple.bean.WorkInfo
import cn.com.base.simple.databinding.WorkInfoComponent

/**
 * 创建日期：2018/6/13 on 15:22
 * 描述:
 * 作者:wantao
 */
class TestRecAdapter(context: Context?, list: List<WorkInfo>) : BaseRecyclerAdapter<WorkInfo>(context, list) {

    override fun getlayoutId(viewType: Int): Int {
        return R.layout.item_test
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createviewHolder(parent: ViewGroup, viewType: Int, itemTestBinding: ViewDataBinding): BaseRecyclerHolder {
        var testViewHolder = TestViewHolder(itemTestBinding.root)
        testViewHolder.itemTestBinding = itemTestBinding as ItemTestBinding
        return testViewHolder;
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        var testViewHolder = holder as TestViewHolder
        testViewHolder.itemTestBinding!!.workInfo = list[position]
        testViewHolder.itemTestBinding!!.executePendingBindings()
    }

    internal inner class TestViewHolder(outer: View) : BaseRecyclerAdapter.BaseRecyclerHolder(outer) {
        var itemTestBinding: ItemTestBinding? = null
    }

    override fun getDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), getlayoutId(viewType), parent, false,WorkInfoComponent())
    }

}
