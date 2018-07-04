package cn.com.base.http

/**
 * 创建日期：2018/6/6 on 15:47
 * 描述:
 * 作者:wantao
 */
abstract  class  ResponseCallback<T> {

    open fun onComplete() {

    }

    open fun onError(e: Throwable) {

    }

    abstract fun onNext(t: T)

}
