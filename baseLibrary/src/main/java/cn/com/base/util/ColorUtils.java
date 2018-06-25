package cn.com.base.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;


/**
 * @author : TJ
 * @date : 2017/12/27 14:24
 * @description :随机生成颜色drawable
 */

public class ColorUtils {

    public static int previousColor = 0; //上一个颜色
    public static int newColor;//新颜色
    public static int[] colors = {Color.rgb(253, 239, 239), Color.rgb(242, 242, 252),
            Color.rgb(240, 248, 250), Color.rgb(255, 248, 230)};

    public static int ColorId() {

        int index = (int) (Math.random() * colors.length);

        if (previousColor == index) {
            return ColorId();
        } else {
            newColor = index;
            previousColor = newColor;
            return colors[newColor];
        }

    }

    /**
     * 根据色值生成图片
     */
    public static Drawable getImageDrawable() {
        ColorDrawable drawable = new ColorDrawable(ColorId());
        Bitmap        bitmap   = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
        Canvas        canvas   = new Canvas(bitmap);
        drawable.draw(canvas);
        return drawable;
    }
}
