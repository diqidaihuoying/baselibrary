package cn.com.base.simple.bean

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import cn.com.base.LibApplication
import cn.com.base.R
import cn.com.base.util.DateUtil

/**
 * 创建日期：2018/6/28 on 14:34
 * 描述:
 * 作者:wantao
 */

class WorkDetail {

    var avatar: String = ""
    var categoryName: String = ""
    var comments: Int = 0
    var cover: String = ""

    var duration: Int = 0
    var favorites: Int = 0
    var favors: Int = 0
    var height: Int = 0
    var hid: Int = 0
    var id: Int = 0
    var imageList: List<String> = listOf()
    var isFavorite: Int = 0
    var isRecommend: Int = 0
    var name: String = ""
    var path: String = ""
    var pv: Int = 0
    var size: Int = 0
    var state: Int = 0
    var summary: String = ""
    var type: Int = 0
    var uid: Int = 0
    var width: Int = 0

    /**
     * 设置是否鲜花
     */
    var isFavor: Int? = 0
    var isFavorBg: Drawable? = null
        get() {
            if (isFavor == 0)
                return ContextCompat.getDrawable(LibApplication.application, R.mipmap.ic_test_flower_unselected)
            else
                return ContextCompat.getDrawable(LibApplication.application, R.mipmap.ic_test_flower_selected)
        }
    /**
     * 设置是否收藏
     */
    var isFollow: Int? = 0
    var isFollowBg: Drawable? = null
        get() {
            if (isFollow == 0)
                return ContextCompat.getDrawable(LibApplication.application, R.mipmap.ic_test_collect_unselected)
            else
                return ContextCompat.getDrawable(LibApplication.application, R.mipmap.ic_test_collect_selected)
        }

    /**
     * 设置时间
     */
    var createtime: Long = 0
    var mCreatetime: String = ""
        get() {
            return DateUtil.getTimeDescrition(createtime)
        }
}
