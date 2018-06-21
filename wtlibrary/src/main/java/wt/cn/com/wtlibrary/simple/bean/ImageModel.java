package wt.cn.com.wtlibrary.simple.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import wt.cn.com.wtlibrary.glide.GlideUtil;
import wt.cn.com.wtlibrary.simple.util.StrUtils;

/**
 * author      : wantao
 * date        : 2017/11/8 14:25
 * description : 图片加载类
 */
abstract public class ImageModel{

    protected String imageUrl;

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        GlideUtil.getInstance().loadCommonImg(view.getContext(), StrUtils.getResourceUrl(imageUrl),view);
    }

    abstract public String getImageUrl();
}