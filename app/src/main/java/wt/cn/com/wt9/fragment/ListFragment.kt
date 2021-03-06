package wt.cn.com.wt9.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.base.base.BaseFragment
import cn.com.base.mvp.BaseFragmentPresent

import wt.cn.com.wt9.R
import wt.cn.com.wt9.databinding.FragmentListBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class ListFragment : BaseFragment<FragmentListBinding,BaseFragmentPresent>() {
    override val layoutId: Int
        get() = R.layout.fragment_list

    override fun initView() {
    }

    override fun initData() {
    }

    override fun createPresenter(): BaseFragmentPresent? {
        return BaseFragmentPresent(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


}
