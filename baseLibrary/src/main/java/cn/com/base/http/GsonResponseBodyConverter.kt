package cn.com.base.http

import cn.com.base.util.LogUtil
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

/**
 * 创建日期：2018/6/6 on 16:17
 * 描述:
 * 作者:wantao
 */
class GsonResponseBodyConverter<T>(private val gson: Gson, private val type: Type) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val response = value.string()
        val httpHttpResult = gson.fromJson(response, HttpResult::class.java)
        if (httpHttpResult.rt == 1) {
            //1的时候就直接解析，不可能出现解析异常。因为我们实体基类中传入的泛型，就是数据成功时候的格式
            try {
                return gson.fromJson<T>(response, type)
            } catch (e: Exception) {
                LogUtil.e("GsonResponseBodyConverter", "convert: 解析异常：数据类型非指定泛型")
                val stringHttpResult = HttpResult<String>()
                return stringHttpResult as T
            }

        } else {
            LogUtil.e("GsonResponseBodyConverter", "rt异常:" + httpHttpResult.rt)
            return httpHttpResult as T
        }
    }
}