package wt.cn.com.wtlibrary.simple.activity

import kotlinx.android.synthetic.main.activity_base.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import wt.cn.com.wtlibrary.R
import wt.cn.com.wtlibrary.base.BaseActivity
import wt.cn.com.wtlibrary.base.BaseFragment
import wt.cn.com.wtlibrary.base.TabAdapter
import wt.cn.com.wtlibrary.databinding.ActivityMainBinding
import wt.cn.com.wtlibrary.eventbus.MessageEvent
import wt.cn.com.wtlibrary.http.ResponseCallback
import wt.cn.com.wtlibrary.simple.bean.ImageModel
import wt.cn.com.wtlibrary.simple.bean.Interest
import wt.cn.com.wtlibrary.simple.fragment.TestFragment
import wt.cn.com.wtlibrary.simple.http.RetrofitHelp
import wt.cn.com.wtlibrary.util.ToastUtils


class MainActivity : BaseActivity() {

    override val layoutId = R.layout.activity_main

    private var dataBinding: ActivityMainBinding? = null
    protected var tabAdapter: TabAdapter? = null
    protected var titles: MutableList<String>? = mutableListOf()
    protected var fragments: MutableList<BaseFragment>? = mutableListOf()


    override fun initView() {
        dataBinding = mDataBinding as ActivityMainBinding?
        dataBinding!!.mod =  ImageModel("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529660364059&di=f10813c98dd635a7f82ec22d8e016ab7&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0142135541fe180000019ae9b8cf86.jpg%401280w_1l_2o_100sh.png")
        tabAdapter = TabAdapter(supportFragmentManager, fragments, titles)
    }

    override fun initData() {
        multiLayout.showLoading()
        applySchedulers(RetrofitHelp.apiService!!.getInterests(RetrofitHelp.getBaseMap())).subscribe(newObserver(object : ResponseCallback<List<Interest>>() {
            override fun onNext(result: List<Interest>) {
                if (result?.size>0)
                {
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
                    multiLayout.showContent()
                }else
                {
                    multiLayout.showEmpty()
                }
            }
        }))
    }

    @Subscribe
    fun receiveMessage(event: MessageEvent)
    {
        ToastUtils.showShort(event.message!!)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}



