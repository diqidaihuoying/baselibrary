package cn.com.base.util

import android.util.Log

/**
 * 创建日期：2018/6/6 on 15:47
 * 描述:
 * 作者:wantao
 */
object LogUtil {
    //当前Debug模式
    var DE_BUG = true
    val TAG = "Wtlibrary----->"

    private val LOG_FORMAT = "%1\$s\n%2\$s"

    fun e(tag: String, text: String) {
        if (DE_BUG) {
            Log.e(setTag(tag), text)
        }
    }

    fun d(tag: String, text: String) {
        if (DE_BUG) {
            Log.d(setTag(tag), text)
        }
    }

    fun i(tag: String, text: String) {
        if (DE_BUG) {
            Log.i(setTag(tag), text)
        }
    }

    fun w(tag: String, text: String) {
        if (DE_BUG) {
            Log.w(setTag(tag), text)
        }
    }

    fun v(tag: String, text: String) {
        if (DE_BUG) {
            Log.v(setTag(tag), text)
        }
    }

    fun d(tag: String, vararg args: Any) {
        log(Log.DEBUG, null, tag, *args)
    }

    fun i(tag: String, vararg args: Any) {
        log(Log.INFO, null, tag, *args)
    }

    fun w(tag: String, vararg args: Any) {
        log(Log.WARN, null, tag, *args)
    }

    fun e(ex: Throwable) {
        log(Log.ERROR, ex, null)
    }

    fun e(tag: String, vararg args: Any) {
        log(Log.ERROR, null, tag, *args)
    }

    fun e(ex: Throwable, tag: String, vararg args: Any) {
        log(Log.ERROR, ex, tag, *args)
    }

    private fun log(priority: Int, ex: Throwable?, tag: String?, vararg args: Any) {

        if (LogUtil.DE_BUG == false) return

        var log = ""
        if (ex == null) {
            if (args != null && args.size > 0) {
                for (obj in args) {
                    log += obj.toString()
                }
            }
        } else {
            val logMessage = ex.message
            val logBody = Log.getStackTraceString(ex)
            log = String.format(LOG_FORMAT, logMessage, logBody)
        }
        Log.println(priority, tag, log)
    }

    fun setDebug(isDebug: Boolean) {
        LogUtil.DE_BUG = isDebug
    }

    fun setTag(tag: String): String {
        return StringBuffer(TAG).append(tag).toString()
    }


}
