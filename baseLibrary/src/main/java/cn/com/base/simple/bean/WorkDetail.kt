package cn.com.base.simple.bean

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
    var createtime: String = ""
        get() {
            return DateUtil.getTimeDescrition(createtime.toLong())
        }
    var duration: Int = 0
    var favorites: Int = 0
    var favors: Int = 0
    var height: Int = 0
    var hid: Int = 0
    var id: Int = 0
    var imageList: List<String> = listOf()
    var isFavor: Int = 0
        get() {
            if (field == 0)
                return R.mipmap.ic_test_flower_unselected
            else
                return R.mipmap.ic_test_flower_selected
        }
    var isFavorite: Int = 0
    var isFollow: Int = 0
        get() {
            if (field == 0)
                return R.mipmap.ic_test_collect_unselected
            else
                return R.mipmap.ic_test_collect_selected
        }
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
}
