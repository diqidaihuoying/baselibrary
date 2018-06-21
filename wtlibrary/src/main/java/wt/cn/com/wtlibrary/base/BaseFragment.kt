package wt.cn.com.wtlibrary.base


import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import wt.cn.com.wtlibrary.http.HttpResult
import wt.cn.com.wtlibrary.http.ResponseCallback

/**
 * A simple [Fragment] subclass.
 */
abstract class BaseFragment : Fragment() {

    protected var mDataBinding: ViewDataBinding? = null
    protected abstract val layoutId: Int
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        mDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initView()
        initData()
        return mDataBinding?.root
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
