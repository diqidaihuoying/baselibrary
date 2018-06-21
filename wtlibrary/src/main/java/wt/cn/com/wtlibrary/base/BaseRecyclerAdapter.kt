package wt.cn.com.wtlibrary.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * 创建日期：2018/6/13 on 15:13
 * 描述: 空实现，以后优化
 * 作者:wantao
 */
abstract class BaseRecyclerAdapter<T>(protected var context: Context?, protected var list: List<T>) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerHolder>() {


    open  class BaseRecyclerHolder(itemView :View) : RecyclerView.ViewHolder(itemView)


}
