package cn.com.base.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 创建日期：2018/6/13 on 15:13
 * 描述: 空实现，以后优化
 * 作者:wantao
 */
abstract class BaseRecyclerAdapter<T>(protected var context: Context?, protected var list: List<T>)
    : RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerHolder>() {

    /**
     * 布局
     */
//    protected abstract val layoutId: Int

    protected abstract fun getlayoutId(position: Int): Int

    var clicklistener: OnRcyClickListener? = null

    var longClicklistener: onRcyLongClickListener? = null

    /**
     * 设置点击Item的点击事件
     */
    fun setOnRcyClickListener(clicklistener: OnRcyClickListener) {
        this.clicklistener = clicklistener;
    }

    fun setOnRcyLongClickListener(longClick: onRcyLongClickListener) {
        this.longClicklistener = longClick;
    }

    //RecyclerView的Item点击事件
    interface OnRcyClickListener {
        fun onRcyClick(parent: ViewGroup, viewType: Int)
    }

    //RecyclerView的Item长按事件
    interface onRcyLongClickListener {
        fun onRcyLongClick(parent: ViewGroup, viewType: Int): Boolean
    }


    protected abstract fun createviewHolder(parent: ViewGroup, viewType: Int, itemTestBinding: ViewDataBinding): BaseRecyclerHolder

    open class BaseRecyclerHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        var dataBinding = getDataBinding(parent,viewType)
        dataBinding.root.setOnClickListener {
            clicklistener?.onRcyClick(parent, viewType)
        }

        dataBinding.root.setOnLongClickListener {
            longClicklistener?.onRcyLongClick(parent, viewType) ?: true
        }
        return createviewHolder(parent, viewType, dataBinding)
    }

    /**
     * 此方法可能会重写
     */
    open fun getDataBinding(parent: ViewGroup, viewType: Int):ViewDataBinding
    {
        return DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), getlayoutId(viewType), parent, false)
    }

}
