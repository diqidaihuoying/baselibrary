package wt.cn.com.wtlibrary.base


import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    protected var mDataBinding: ViewDataBinding? = null
    protected abstract val layoutId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initView()
        initData()
        return mDataBinding?.root
    }

    abstract fun initView()

    abstract fun initData()
}
