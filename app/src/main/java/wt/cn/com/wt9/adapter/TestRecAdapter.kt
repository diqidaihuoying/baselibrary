package wt.cn.com.wt9.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import wt.cn.com.wt9.R
import wt.cn.com.wtlibrary.base.BaseRecyclerAdapter
import wt.cn.com.wtlibrary.glide.GlideUtil

/**
 * 创建日期：2018/6/13 on 15:22
 * 描述:
 * 作者:wantao
 */
class TestRecAdapter(context: Context, list: List<String>) : BaseRecyclerAdapter<String>(context, list) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var view = LayoutInflater.from(context).inflate(R.layout.item_test,null)
        var testViewHolder = TestViewHolder(view)
        return testViewHolder
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var testViewHolder=holder as TestViewHolder
        GlideUtil.getInstance().loadCommonImg(context,list[position],testViewHolder.imageView)

    }


    internal inner class TestViewHolder(outer: View) : BaseRecyclerAdapter.BaseRecyclerHolder(outer)
    {
        var imageView = outer.findViewById<ImageView>(R.id.iv)
    }



}
