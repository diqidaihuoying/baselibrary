package wt.cn.com.wt9.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.base.base.BaseRecyclerAdapter
import wt.cn.com.wt9.R
import wt.cn.com.wt9.bean.Works
import wt.cn.com.wt9.databinding.ItemShowChildRecBinding
import wt.cn.com.wt9.databinding.WorkInfoComponent

/**
 * 创建日期：2018/7/6 on 10:16
 * 描述:
 * 作者:wantao
 */
class ShowChildAdapter(context: Context?,list: MutableList<Works>) : BaseRecyclerAdapter<Works> (context,list){
    override fun getlayoutId(viewType: Int): Int {
        return R.layout.item_show_child_rec
    }

    override fun createviewHolder(parent: ViewGroup, viewType: Int, itemTestBinding: ViewDataBinding): BaseRecyclerHolder {
        var viewHolder=ViewHolder(itemTestBinding.root)
        viewHolder.itemShowChildBinding=itemTestBinding as ItemShowChildRecBinding
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        var itemShowChildBinding=(holder as ViewHolder).itemShowChildBinding
        itemShowChildBinding!!.work=list[position]
        itemShowChildBinding!!.executePendingBindings()
    }

    override fun getDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), getlayoutId(viewType), parent, false,WorkInfoComponent())
    }

    class ViewHolder(view :View ) :BaseRecyclerHolder(view)
    {
        var itemShowChildBinding:ItemShowChildRecBinding?=null
    }


}