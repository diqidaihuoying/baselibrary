package cn.com.base.base


import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.base.R
import cn.com.base.databinding.FragmentBaseBinding
import cn.com.base.mvp.BaseFragmentPresent
import cn.com.base.mvp.MvpView

/**
 * A simple [Fragment] subclass.
 */
public abstract class BaseFragment<B : ViewDataBinding,P : BaseFragmentPresent> : Fragment(),MvpView {

    protected var mDataBinding: B? = null
    private var mFragmentDataBinding :FragmentBaseBinding?= null
    protected abstract val layoutId: Int
    protected var basePresenter :P?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mFragmentDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        //添加内容布局
        mDataBinding= getDatabinding(inflater, layoutId, mFragmentDataBinding!!.root as ViewGroup,false)
        mFragmentDataBinding!!.container!!.addView(mDataBinding!!.root)
        basePresenter=createPresenter();
        initView()
        initData()
        return mFragmentDataBinding?.root
    }

    override fun showNoNetwork() {
       mFragmentDataBinding!!.multiLayout.showNoNetwork()
    }

    override fun showContent() {
        mFragmentDataBinding!!.multiLayout.showContent()
    }

    /**
     * 可重写
     */
    open protected fun getDatabinding(from: LayoutInflater?, layoutId: Int, viewGroup: ViewGroup, b: Boolean): B? {
        return DataBindingUtil.inflate(from!!,layoutId,viewGroup,b)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        basePresenter!!.onDestroyHttp()
    }


    abstract fun initView()

    abstract fun initData()

    abstract fun createPresenter(): P?
}
