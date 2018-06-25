package cn.com.base.util

import android.text.TextUtils
import android.widget.Toast
import cn.com.base.LibApplication



/**
 * Toast 工具类
 */

object ToastUtils {
    var isShow = true

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    fun showShort(message: CharSequence) {
        var message = message
        if (TextUtils.isEmpty(message)) {
            message = ""
        }
        if (isShow) {
            Toast.makeText(LibApplication.application, message, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    fun showShort(message: Int) {
        if (isShow) {
            Toast.makeText(LibApplication.application, message, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    fun showLong(message: CharSequence) {
        var message = message
        if (TextUtils.isEmpty(message)) {
            message = ""
        }
        if (isShow) {
            Toast.makeText(LibApplication.application, message, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    fun showLong(message: Int) {
        if (isShow) {
            Toast.makeText(LibApplication.application, message, Toast.LENGTH_LONG).show()
        }

    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    fun show(message: CharSequence, duration: Int) {
        var message = message
        if (TextUtils.isEmpty(message)) {
            message = ""
        }
        if (isShow) {
            Toast.makeText(LibApplication.application, message, duration).show()
        }

    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    fun show(message: Int, duration: Int) {
        if (isShow) {
            Toast.makeText(LibApplication.application, message, duration).show()
        }
    }

}
