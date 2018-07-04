package cn.com.base.mvp

import cn.com.base.http.HttpResult
import cn.com.base.http.ResponseCallback
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * 创建日期：2018/7/4 on 10:09
 * 描述:
 * 作者:wantao
 */
open class BaseHttpPresent(view: MvpView)  {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()


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

    fun onDestroyHttp()
    {
        mCompositeDisposable!!.clear()
        mCompositeDisposable!!.dispose()
    }
}