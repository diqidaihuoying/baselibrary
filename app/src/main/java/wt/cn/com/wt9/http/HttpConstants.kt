package wt.cn.com.wt9.http

import wt.cn.com.wtlibrary.util.MD5Utils

/**
 * 创建日期：2018/6/6 on 15:36
 * 描述:
 * 作者:wantao
 */
object
HttpConstants {

    val BASE_URL = "http://192.168.0.239:8081/show/"
    val APPKEY = "1342d703d3fa492897f4b706ca8795ec"
    val APP_SECRET = "6b7c718ab6174710a6e5139055918123"

    val SIGN_KEY = "cappu-g@od"
    val SIGN = MD5Utils.md5Encode(APP_SECRET + SIGN_KEY)
}
