package wt.cn.com.wtlibrary.simple.activity

import wt.cn.com.wtlibrary.R
import wt.cn.com.wtlibrary.base.BaseActivity
import wt.cn.com.wtlibrary.base.BaseFragment
import wt.cn.com.wtlibrary.base.TabAdapter
import wt.cn.com.wtlibrary.databinding.ActivityMainBinding
import wt.cn.com.wtlibrary.http.ResponseCallback
import wt.cn.com.wtlibrary.simple.bean.ImageModel
import wt.cn.com.wtlibrary.simple.bean.Interest
import wt.cn.com.wtlibrary.simple.fragment.TestFragment
import wt.cn.com.wtlibrary.simple.http.RetrofitHelp


class MainActivity : BaseActivity() {

    override val layoutId = R.layout.activity_main

    private var dataBinding: ActivityMainBinding? = null
    protected var tabAdapter: TabAdapter? = null
    protected var titles: MutableList<String>? = mutableListOf()
    protected var fragments: MutableList<BaseFragment>? = mutableListOf()


    override fun initView() {
        dataBinding = mDataBinding as ActivityMainBinding?
        dataBinding!!.mod = object : ImageModel()
        {
            override fun getImageUrl(): String {
              return ""
            }
        }

        tabAdapter = TabAdapter(supportFragmentManager, fragments, titles)
    }

    override fun initData() {
        applySchedulers(RetrofitHelp.apiService!!.getInterests(RetrofitHelp.getBaseMap())).subscribe(newObserver(object : ResponseCallback<List<Interest>>() {
            override fun onNext(result: List<Interest>) {
                titles!!.clear()
                fragments!!.clear()
                result.forEach(
                        {
                            titles!!.add(it.name)
                            fragments!!.add(TestFragment())
                        }
                )
                dataBinding!!.viewPager.adapter =tabAdapter
                dataBinding!!.tabLayout.setupWithViewPager(dataBinding!!.viewPager);
            }
        }))
    }

}



