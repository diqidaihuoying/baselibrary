package cn.com.base.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import cn.com.base.R


/**
 * 创建日期：2018/6/22 on 10:35
 * 描述:
 * 作者:wantao
 */
class MultiLayout(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var mInflater: LayoutInflater? = LayoutInflater.from(context)
    var mContentId = View.NO_ID
    var mLayouts: HashMap<Int, View?> = HashMap()
    var typeArray = context!!.obtainStyledAttributes(attrs, R.styleable.MultiLayout) as TypedArray

    /**
     * 空视图
     */
    var mEmptyResId = typeArray.getResourceId(R.styleable.MultiLayout_llEmptyResId, R.layout.layout_empty);
    var mEmptyView: View = mInflater!!.inflate(mEmptyResId, this, false)

    interface IonEmptyViewClickListener {
        fun onEmptyViewClickListener(view: View)
    }

    var onEmptyViewClickListener: IonEmptyViewClickListener? = null

    /**
     * 无网络视图
     */
    var mNoNetworkResId = typeArray.getResourceId(R.styleable.MultiLayout_llNoNetworkResId, R.layout.layout_nonetwork);
    var mNoNetworkView: View = mInflater!!.inflate(mNoNetworkResId, this, false)

    /**
     * 加载视图
     */
    var mLoadingResId = typeArray.getResourceId(R.styleable.MultiLayout_llLoadingResId, R.layout.layout_loading);
    var mLoadingView: View = mInflater!!.inflate(mLoadingResId, this, false)

    init {
        typeArray.recycle()
        mEmptyView.setOnClickListener {
            object : OnClickListener {
                override fun onClick(v: View?) {
                    onEmptyViewClickListener?.onEmptyViewClickListener(mEmptyView)
                }
            }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        when (childCount) {
            0 -> return
            1 -> {
                var view = getChildAt(0)
                mContentId = view.getId()
                mLayouts.put(mContentId, view)
            }
            else -> {
                removeViews(1, getChildCount() - 1);
            }
        }
    }

    /**
     * 显示内容视图
     */
    fun showContent() {
        show(mContentId)
    }

    /**
     * 显示空视图
     */
    fun showEmpty() {
        show(mEmptyResId);
    }

    /**
     * 显示无网络
     */
    fun showNoNetwork() {
        show(mNoNetworkResId)
    }

    /**
     * 显示加载
     */
    fun showLoading() {
        show(mLoadingResId)
    }

    private fun show(layoutId: Int) {
        for (view in mLayouts.values) {
            view!!.setVisibility(View.GONE)
        }
        layout(layoutId)?.setVisibility(View.VISIBLE)
    }

    private fun layout(layoutId: Int): View? {
        if (mLayouts.containsKey(layoutId)) {
            return mLayouts[layoutId]
        }
        var layout =
                if (layoutId == mEmptyResId)
                    mEmptyView
                else if (layoutId == mNoNetworkResId)
                    mNoNetworkView
                else if (layoutId == mLoadingResId)
                    mLoadingView
                else
                    throw Exception("不识别的类型view")
        addView(layout)
        mLayouts[layoutId] = layout
        return layout
    }

}