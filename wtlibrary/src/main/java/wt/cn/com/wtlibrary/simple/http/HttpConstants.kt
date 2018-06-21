package wt.cn.com.wtlibrary.simple.http

import wt.cn.com.wtlibrary.util.MD5Utils

/**
 * 创建日期：2018/6/6 on 15:36
 * 描述:
 * 作者:wantao
 */
 object
HttpConstants {

    val BASE_URL = "http://show.cappu.cn/acqierement/"
    val APPKEY = "a63eb2115ea940a69d10feac79a57a25"
    val APP_SECRET = "aa0c1b2a6f934b3c9c33821629de1c8c"
    val SIGN_KEY = "cappu-g@od"
    val SIGN = MD5Utils.md5Encode(APP_SECRET + SIGN_KEY)
}
