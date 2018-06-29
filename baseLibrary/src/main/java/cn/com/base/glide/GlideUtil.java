package cn.com.base.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import cn.com.base.R;
import cn.com.base.util.ColorUtils;

/**
 * 创建日期：2018/6/12 on 19:11
 * 描述:
 * 作者:wantao
 */
public class GlideUtil {

    private static GlideUtil glideUtil;

    public static GlideUtil getInstance() {
        if (glideUtil == null) {
            glideUtil = new GlideUtil();
        }
        return glideUtil;
    }

    public void loadCommonImg(Context context, String url, ImageView imageView) {
        RequestOptions colorOptions = new RequestOptions()
                .placeholder(ColorUtils.getImageDrawable())
                .error(ColorUtils.getImageDrawable())
                .centerCrop();
        Glide.with(context)
                .load(url)
                .apply(colorOptions)
                .into(imageView);
    }


    /**
     * 加载圆形图像
     * @param context
     * @param url
     * @param imageView
     */
    public void loadCircleImg(Context context, String url, ImageView imageView)
    {
        RequestOptions requestOptions = RequestOptions.bitmapTransform(new CircleCrop())
                .placeholder(R.mipmap.ic_default_head_portrait)
                .error(R.mipmap.ic_default_head_portrait);
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }


}
