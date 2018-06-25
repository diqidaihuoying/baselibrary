package cn.com.base.http

import cn.com.base.LibApplication
import cn.com.base.util.NetWorkUtil
import java.io.IOException

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 创建日期：2018/6/6 on 16:01
 * 描述:
 * 作者:wantao
 */
class BaseInterceptor : Interceptor {

    internal lateinit var progressRequestListener: ProgressRequestBody.ProgressRequestListener

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("X-HB-Client-Type", "ayb-android")
                .addHeader("Content-Type", "multipart/form-data; boundary=7db372eb000e2")
                .build()
        val method = request.method()
        if (method == "POST") {
            request = request.newBuilder().method(request.method(), ProgressRequestBody(request.body()!!, progressRequestListener)).build()
        }
        if (NetWorkUtil.isNetworkAvailable(LibApplication.application)) {
            request = request.newBuilder().cacheControl(CacheControl.Builder().noCache().build()).build()
        }
        return chain.proceed(request)
    }

    fun setProgressRequestListener(progressRequestListener: ProgressRequestBody.ProgressRequestListener) {
        this.progressRequestListener = progressRequestListener
    }
}
