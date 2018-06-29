package cn.com.base.simple.bean

import cn.com.base.R
import cn.com.base.util.DateUtil
import cn.com.base.util.LogUtil

/**
 * 创建日期：2018/6/28 on 14:34
 * 描述:
 * 作者:wantao
 */

class WorkDetail(
        var avatar: String,
        var comments: Int,
        var cover: String,
        var duration: Int,
        var favorites: Int,
        var favors: Int,
        var height: Int,
        var hid: Int,
        var id: Int,
        var imageList: List<String>,
        var isFavorite: Int,
        var isRecommend: Int,
        var name: String,
        var path: String,
        var pv: Int,
        var size: Int,
        var state: Int,
        var summary: String,
        var type: Int,
        var uid: Int,
        var width: Int,
        var isFavor: Int,
        var isFollow: Int,
        var createtime: String
) {
    init {
        LogUtil.e("WorkDetail", createtime)
        isFavor =
                if (isFavor == 0)
                    R.mipmap.ic_test_flower_unselected
                else
                    R.mipmap.ic_test_flower_selected

        isFollow =
                if (isFollow == 0)
                    R.mipmap.ic_test_collect_unselected
                else
                    R.mipmap.ic_test_collect_selected

        createtime = DateUtil.getTimeDescrition(createtime.toLong())
    }

}
