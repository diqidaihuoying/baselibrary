/*
 * Copyright (c) 2014,KJFrameForAndroid Open Source Project,张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.base.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import cn.com.base.LibApplication;

public final class DensityUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        Resources r = LibApplication.application.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, r.getDisplayMetrics());
        return (int) px;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = LibApplication.application.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static int px2sp( float pxValue) {
        float fontScale = LibApplication.application.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px
     */
    public static int sp2px(float spValue) {
        float fontScale = LibApplication.application.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取dialog宽度
     */
    public static int getDialogW() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = LibApplication.application.getResources().getDisplayMetrics();
        int w = dm.widthPixels - 100;
        return w;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenW() {
        DisplayMetrics dm = LibApplication.application.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenH() {
        DisplayMetrics dm = LibApplication.application.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    /**
     * 状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        Resources resources  = LibApplication.application.getResources();
        int       resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int       height     = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 得到底部导航栏的高度
     *
     * @return
     */
    public static int getNavigationBarHeight() {
        Resources resources  = LibApplication.application.getResources();
        int       resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int       height     = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 判断是否有导航栏
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean IsNavigationBar(WindowManager wm) {
        Display        defaultDisplay     = wm.getDefaultDisplay();
        DisplayMetrics realdisplayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(realdisplayMetrics);
        int realheightPixels = realdisplayMetrics.heightPixels;
        int realwidthPixels  = realdisplayMetrics.widthPixels;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int heightPixels = displayMetrics.heightPixels;
        int widthPixels  = displayMetrics.widthPixels;
        return realheightPixels > heightPixels || realwidthPixels > widthPixels;
    }

    /**
     * 字体适配
     */
    public static int FontSize(int screensize, int fontSize) {
        int rate = (int) (fontSize * (float) screensize / 320); //这个倍数比较适合，当然你可以测试后再修改
        return rate < 15 ? 15 : rate; //字体太小也不好看的  1080 3
    }


}