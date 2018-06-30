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
import cn.com.base.http.HttpResult
import cn.com.base.http.ResponseCallback
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 */
public abstract class BaseFragment : Fragment() {

    protected var mDataBinding: ViewDataBinding? = null
    protected var mFragmentDataBinding :FragmentBaseBinding?= null
    protected abstract val layoutId: Int
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mFragmentDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        //添加内容布局
        mDataBinding= getDatabinding(inflater, layoutId, mFragmentDataBinding!!.root as ViewGroup,false)
        mFragmentDataBinding!!.container!!.addView(mDataBinding!!.root)
        initView()
        initData()
        return mFragmentDataBinding?.root
    }
    /**
     * 可重写
     */
    open protected fun getDatabinding(from: LayoutInflater?, layoutId: Int, viewGroup: ViewGroup, b: Boolean): ViewDataBinding? {
        return DataBindingUtil.inflate(from!!,layoutId,viewGroup,b)
    }

    protected fun <T> newObserver(callback: ResponseCallback<T>): Observer<T> {
        return object : Observer<T> {
            override fun onError(e: Throwable) {
                callback.onError(e)
            }

            override fun onComplete() {
                callback.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                mCompositeDisposable!!.add(d)
            }

            override fun onNext(t: T) {
                if (!mCompositeDisposable!!.isDisposed) {
                    callback.onNext(t)
                }
            }
        }
    }
    /**
     * 对 Observable<HttpResult></HttpResult><T>> 做统一的处理，处理了线程调度、分割返回结果等操作组合了起来
     *
     * @param responseObservable
     * @param <T>
     * @return
    </T></T> */
    protected fun <T> applySchedulers(responseObservable: Observable<HttpResult<T>>): Observable<T> {
        return responseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { tHttpResult -> tHttpResult.data }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mCompositeDisposable != null) {
            mCompositeDisposable!!.clear()
            mCompositeDisposable!!.dispose()
        }
    }


    abstract fun initView()

    abstract fun initData()


}
