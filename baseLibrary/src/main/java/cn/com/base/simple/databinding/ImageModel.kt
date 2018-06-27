package cn.com.base.simple.databinding

import android.databinding.BindingAdapter
import android.databinding.DataBindingComponent
import android.widget.ImageView
import cn.com.base.glide.GlideUtil
import cn.com.base.simple.util.StrUtils

/**
 * author      : wantao
 * date        : 2017/11/8 14:25
 * description : 图片
 */
open abstract class ImageModel : DataBindingComponent {

    override fun getImageModel(): ImageModel {
        return this
    }

    /**
     * 图片加载
     *
     * @param view
     * @param imageUrl
     */
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        GlideUtil.getInstance().loadCommonImg(view.context, StrUtils.getResourceUrl(imageUrl), view)
    }


}