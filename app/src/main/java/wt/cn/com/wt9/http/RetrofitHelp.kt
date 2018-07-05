package wt.cn.com.wt9.http

import cn.com.base.LibApplication
import cn.com.base.http.BaseInterceptor
import cn.com.base.http.NetInterceptor
import cn.com.base.http.ProgressRequestBody
import cn.com.base.http.ResponseConverterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.util.HashMap
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * 创建日期：2018/6/6 on 15:32
 * 描述:
 * 作者:wantao
 */
object RetrofitHelp {
    private val baseInterceptor = BaseInterceptor()
    private var retrofit: Retrofit? = null
    var apiService : ApiService?= null
    init {
        initRetorit()
    }

    private fun initRetorit() {
        //log拦截器打印所有的log
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        //设置请求的缓存
        val cacheFile = File(LibApplication.application.cacheDir, "http_cache")
        val cache = Cache(cacheFile, (1024 * 1024 * 50).toLong()) //50Mb
        //配置client
        val client = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)// 15秒连接超时
                .addInterceptor(loggingInterceptor)
                .addInterceptor(baseInterceptor)
                .addNetworkInterceptor(NetInterceptor())
                .cache(cache)// 设置缓存文件
                .build()
        val executorService = Executors.newFixedThreadPool(1)
        retrofit = Retrofit.Builder()
                // 使用OkHttp Client
                .client(client)
                // baseUrl总是以/结束，@URL不要以/开头
                .baseUrl(HttpConstants.BASE_URL)
                // 集成Gson转换器
                .addConverterFactory(ResponseConverterFactory.create())
                // 集成RxJava处理
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .callbackExecutor(executorService) //默认CallBack回调在主线程进行,当设置下载大文件时需设置注解@Stream 不加这句话会报android.os.NetworkOnMainThreadException
                .build()

        apiService = retrofit!!.create(ApiService::class.java)
    }

    /**
     * 设置上传的监听
     * @param progressRequestListener
     */
    fun setProgressRequestListener(progressRequestListener: ProgressRequestBody.ProgressRequestListener) {
        baseInterceptor.setProgressRequestListener(progressRequestListener)
    }

    /**
     * 基础参数
     */
    fun getBaseMap() : Map<String,String>
    {
        val map = HashMap<String, String>()
        map.put("sign", HttpConstants.SIGN)
        map.put("appkey", HttpConstants.APPKEY)
        return  map
    }
}
