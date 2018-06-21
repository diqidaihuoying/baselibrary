package wt.cn.com.wtlibrary.simple.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wt.cn.com.wtlibrary.R
import wt.cn.com.wtlibrary.base.BaseRecyclerAdapter
import wt.cn.com.wtlibrary.databinding.ItemTestBinding
import wt.cn.com.wtlibrary.simple.bean.WorkInfo
import wt.cn.com.wtlibrary.util.DensityUtils

/**
 * 创建日期：2018/6/13 on 15:22
 * 描述:
 * 作者:wantao
 */
class TestRecAdapter(context: Context?, list: List<WorkInfo>) : BaseRecyclerAdapter<WorkInfo>(context, list) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        var itemTestBinding = DataBindingUtil.inflate<ItemTestBinding>(LayoutInflater.from(context), R.layout.item_test, parent, false)
        itemTestBinding.root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(context, 200f))
        var testViewHolder = TestViewHolder(itemTestBinding.root)
        testViewHolder.itemTestBinding = itemTestBinding
        return testViewHolder
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        var testViewHolder = holder as TestViewHolder
        testViewHolder.itemTestBinding!!.workInfo = list[position]
        testViewHolder.itemTestBinding!!.executePendingBindings()
    }


    internal inner class TestViewHolder(outer: View) : BaseRecyclerAdapter.BaseRecyclerHolder(outer) {
        var itemTestBinding: ItemTestBinding? = null
    }


}
