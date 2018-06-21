package wt.cn.com.wtlibrary.simple.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wt.cn.com.wtlibrary.R
import wt.cn.com.wtlibrary.base.BaseRecyclerAdapter
import wt.cn.com.wtlibrary.databinding.ItemTestBinding
import wt.cn.com.wtlibrary.databinding.NewitemTestBinding
import wt.cn.com.wtlibrary.simple.bean.WorkInfo
import wt.cn.com.wtlibrary.util.DensityUtils

/**
 * 创建日期：2018/6/13 on 15:22
 * 描述:
 * 作者:wantao
 */
class TestRecAdapter(context: Context?, list: List<WorkInfo>) : BaseRecyclerAdapter<WorkInfo>(context, list) {

    override fun getlayoutId(viewType: Int): Int {
        when (viewType) {
            0 -> return R.layout.item_test
            1 -> return R.layout.newitem_test
            else -> return R.layout.newitem_test
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun createviewHolder(parent: ViewGroup, viewType: Int, itemTestBinding: ViewDataBinding): BaseRecyclerHolder {
        itemTestBinding.root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(context, 200f))
        if (itemTestBinding is ItemTestBinding) {
            var testViewHolder = TestViewHolder(itemTestBinding.root)
            testViewHolder.itemTestBinding = itemTestBinding
            return testViewHolder;
        } else {
            var testViewHolder = TestViewHolder(itemTestBinding.root)
            testViewHolder.itemTestBinding = itemTestBinding as NewitemTestBinding
            return testViewHolder;
        }
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        var testViewHolder = holder as TestViewHolder
        if(testViewHolder.itemTestBinding is ItemTestBinding){

            (testViewHolder.itemTestBinding as ItemTestBinding)?.workInfo = list[position]

        }else if(testViewHolder.itemTestBinding is NewitemTestBinding){

            (testViewHolder.itemTestBinding as NewitemTestBinding)?.workInfo = list[position]

        }
        testViewHolder.itemTestBinding!!.executePendingBindings()
    }


    internal inner class TestViewHolder(outer: View) : BaseRecyclerAdapter.BaseRecyclerHolder(outer) {
        var itemTestBinding: ViewDataBinding? = null
    }

    override fun getItemViewType(position: Int): Int {
        if (position % 2 == 0) {
            return 0;
        }
        return 1;
    }

}
