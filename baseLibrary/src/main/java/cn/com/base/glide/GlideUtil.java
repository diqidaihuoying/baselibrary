package cn.com.base.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cn.com.base.util.ColorUtils;

/**
 * 创建日期：2018/6/12 on 19:11
 * 描述:
 * 作者:wantao
 */
public class GlideUtil {

    private static GlideUtil glideUtil;

    private RequestOptions colorOptions = new RequestOptions()
            .placeholder(ColorUtils.ColorId())
            .error(ColorUtils.ColorId());

    public static GlideUtil getInstance() {
        if (glideUtil == null) {
            glideUtil = new GlideUtil();
        }
        return glideUtil;
    }

    public void loadCommonImg(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(colorOptions)
                .into(imageView);
    }

}
