package cn.com.base.simple.activity
import android.databinding.ViewDataBinding
import android.support.v4.app.Fragment
import android.widget.Toast
import cn.com.base.R
import cn.com.base.base.BaseActivity
import cn.com.base.base.BaseFragment
import cn.com.base.base.TabAdapter
import cn.com.base.databinding.ActivityMainBinding
import cn.com.base.mvp.BaseHttpPresent
import cn.com.base.simple.bean.Interest
import cn.com.base.simple.fragment.TestFragment
import cn.com.base.simple.mvp.MainContact
import cn.com.base.simple.mvp.MainPresenter
import cn.com.base.views.TitleBarView


class MainActivity : BaseActivity <ActivityMainBinding,MainPresenter>(),MainContact.IMainMvpView {

    override fun createPresent(): MainPresenter {
        return MainPresenter(this)
    }

    override val layoutId = R.layout.activity_main

    protected var tabAdapter: TabAdapter? = null
    protected var titles: MutableList<String>? = mutableListOf()
    protected var fragments: MutableList<Fragment>? = mutableListOf()


    override fun initTitle(titile: TitleBarView) {
        titile.SetTitleBarClickListener(TitleBarView.OnTitleBarClickListener { view, direction ->

            if(direction == TitleBarView.LEFTBUTTON){
                Toast.makeText(this,"点击了左边的按钮",Toast.LENGTH_LONG).show()
            }else if(direction == TitleBarView.RIGHTBUTTON){
                Toast.makeText(this,"点击了右边的按钮",Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun initView() {
        tabAdapter = TabAdapter(supportFragmentManager, fragments, titles)
    }

    override fun initData() {
        basePresent!!.getVpData()

    }
    override fun showVpData(result: List<Interest>) {
        titles!!.clear()
        fragments!!.clear()
        result.forEach(
                {
                    titles!!.add(it.name)
                    fragments!!.add(TestFragment.newInstance(it.id))
                }
        )
        mDataBinding!!.viewPager.adapter = tabAdapter
        mDataBinding!!.tabLayout.setupWithViewPager(mDataBinding!!.viewPager);
    }



}



