package cn.com.base.simple.bean

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import cn.com.base.BR
import cn.com.base.LibApplication
import cn.com.base.R
import cn.com.base.util.DateUtil

/**
 * 创建日期：2018/6/30 on 10:28
 * 描述:
 * 作者:wantao
 */

data class WorkDetail(
        var avatar: String,
        var categoryName: String,
        var comments: Int,
        var cover: String,
        private var createtime: Long,
        var duration: Int,
        var favorites: Int,
        var favors: Int,
        var height: Int,
        var hid: Int,
        var id: Int,
        var imageList: List<String>,
        private var isFavor: Int,
        var isFavorite: Int,
        private var isFollow: Int,
        var isRecommend: Int,
        var name: String,
        var path: String,
        var pv: Int,
        var size: Int,
        var state: Int,
        var summary: String,
        var type: Int,
        var uid: Int,
        var width: Int
) : BaseObservable() {


    var favorBg: Drawable
        @Bindable
        get() {
            if (mIsFavor == 0) {
                return getDrawable(R.mipmap.ic_test_flower_unselected)
            } else {
                return getDrawable(R.mipmap.ic_test_flower_selected)
            }
        }
        set(value) {
        }

    var mIsFavor = isFavor
        set(value) {
            field = value
            notifyPropertyChanged(BR.favorBg)
        }

    var followBg: Drawable
        @Bindable
        get() {
            if (mIsFollow == 0) {
                return getDrawable(R.mipmap.ic_test_collect_unselected)
            } else {
                return getDrawable(R.mipmap.ic_test_collect_selected)
            }
        }
        set(value) {
        }

    var mIsFollow = isFollow
        set(value) {
            field = value
            notifyPropertyChanged(BR.followBg)
        }

    var mCreateTime: String
        get() {
            return DateUtil.getTimeDescrition(createtime)
        }
        set(value) {}

    private fun getDrawable(resourceId: Int): Drawable {
        return ContextCompat.getDrawable(LibApplication.application, resourceId)!!
    }

}