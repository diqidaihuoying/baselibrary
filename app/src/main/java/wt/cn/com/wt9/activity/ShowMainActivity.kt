package wt.cn.com.wt9.activity

import android.support.annotation.MainThread
import android.widget.RadioGroup
import android.widget.Toast
import cn.com.base.base.BaseActivity
import cn.com.base.http.HttpResult
import cn.com.base.http.ResponseCallback
import cn.com.base.views.TitleBarView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import skin.support.annotation.Skinable
import wt.cn.com.wt9.R
import wt.cn.com.wt9.bean.Interest
import wt.cn.com.wt9.bean.Works
import wt.cn.com.wt9.databinding.ActivityShowMainBinding
import wt.cn.com.wt9.http.RetrofitHelp
import wt.cn.com.wt9.mvp.present.ShowMainActivityPresent
import wt.cn.com.wt9.mvp.contact.ShowMainContact

class ShowMainActivity : BaseActivity<ActivityShowMainBinding, ShowMainActivityPresent>(), ShowMainContact.IShowMainMvpView, RadioGroup.OnCheckedChangeListener {


    override val layoutId: Int
        get() = R.layout.activity_show_main

    override fun initView() {
        basePresent!!.initFragment()
        mDataBinding!!.rgMenu.setOnCheckedChangeListener(this)
    }

    override fun initData() {
    }

    override fun initTitle(titile: TitleBarView) {
        titile.setTitlename("才艺大道")
        titile.SetTitleBarClickListener(TitleBarView.OnTitleBarClickListener { view, direction ->

            if (direction == TitleBarView.LEFTBUTTON) {
                Toast.makeText(this, "点击了左边的按钮", Toast.LENGTH_LONG).show()
            } else if (direction == TitleBarView.RIGHTBUTTON) {
                Toast.makeText(this, "点击了右边的按钮", Toast.LENGTH_LONG).show()
            }
        })
        RetrofitHelp.apiService!!.getInterests(null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                map (object :Function <HttpResult<List<Interest>>,List<Interest>>{
                    override fun apply(t: HttpResult<List<Interest>>): List<Interest>? {
                       return t.data
                    }

                }).flatMap(object : Function<List<Interest>, Observable<HttpResult<List<Works>>>> {
            override fun apply(t: List<Interest>): Observable<HttpResult<List<Works>>> {
                return RetrofitHelp.apiService!!.getWorkList(null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            }
        }).subscribe()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_show -> basePresent!!.switchFragment(0)
            R.id.rb_list -> basePresent!!.switchFragment(1)
            R.id.rb_message -> basePresent!!.switchFragment(2)
            R.id.rb_me -> basePresent!!.switchFragment(3)
        }

    }

    override fun createPresent(): ShowMainActivityPresent {
        return ShowMainActivityPresent(this)
    }

    override fun showPublishDialog() {
    }
}

