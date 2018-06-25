package cn.com.base.http

/**
 * 创建日期：2018/6/6 on 15:47
 * 描述:
 * 作者:wantao
 */
abstract  class  ResponseCallback<T> {

    fun onComplete() {

    }

    fun onError(e: Throwable) {

    }

    abstract fun onNext(t: T)

}
