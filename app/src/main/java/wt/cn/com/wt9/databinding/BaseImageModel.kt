package wt.cn.com.wt9.databinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import cn.com.base.glide.GlideUtil
import wt.cn.com.wt9.util.StrUtils

/**
 * author      : wantao
 * date        : 2017/11/8 14:25
 * description : 图片
 */
open  class BaseImageModel {

    /**
     * 图片加载
     *
     * @param view
     * @param imageUrl
     */
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String?) {
        GlideUtil.getInstance().loadCommonImg(view.context, StrUtils.getResourceUrl(imageUrl), view)
    }

    /**
     * 头像加载
     *
     * @param view
     * @param imageUrl
     */
    @BindingAdapter("imageUrlAvatar")
    fun loadImageAvatar(view: ImageView, userId: Int?) {
        GlideUtil.getInstance().loadCircleImg(view.context, StrUtils.getAvatar(userId!!), view)
    }

    /**
     * 图片加载
     *
     * @param view
     * @param imageUrl
     */
    @BindingAdapter("imageUrlReal")
    fun loadImageReal(view: ImageView, imageUrl: String?) {
        GlideUtil.getInstance().loadCommonImg(view.context, imageUrl, view)
    }

}