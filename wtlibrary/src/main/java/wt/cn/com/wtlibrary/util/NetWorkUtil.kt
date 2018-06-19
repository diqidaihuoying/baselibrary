package wt.cn.com.wtlibrary.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Build
import android.util.Log

/**
 * 创建日期：2018/6/6 on 15:58
 * 描述:
 * 作者:wantao
 */
object NetWorkUtil {


    /**
     * 检查当前网络是否可用
     *
     * @param context
     * @return
     */
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //新版本调用方法获取网络状态
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networks = connectivityManager.allNetworks
            var networkInfo: NetworkInfo
            for (mNetwork in networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork)
                if (networkInfo.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        } else {
            //否则调用旧版本方法
            if (connectivityManager != null) {
                val info = connectivityManager.allNetworkInfo
                if (info != null) {
                    for (anInfo in info) {
                        if (anInfo.state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
        }
        return false
    }

}
