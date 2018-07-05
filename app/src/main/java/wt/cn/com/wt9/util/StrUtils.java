package wt.cn.com.wt9.util;

import android.text.TextUtils;

import wt.cn.com.wt9.http.HttpConstants;


/**
 * @author : tj
 * @date : 2017/5/27 17:29
 * @description : 字符串处理工具类
 */
public class StrUtils {

    /**
     * 拼接字符串
     *
     * @param strs
     * @return
     */
    public static String plusString(String... strs) {
        StringBuilder builder = new StringBuilder();
        String[]      var2    = strs;
        int           var3    = strs.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String str = var2[var4];
            if (!TextUtils.isEmpty(str)) {
                builder.append(str);
            }
        }
        return builder.toString();
    }

    /**
     * 获取资源的URL
     *
     * @param imgPath
     * @return
     */
    public static String getResourceUrl(String imgPath) {
        String path;
        if (imgPath == null) {
            return "";
        }
        if (imgPath.startsWith("/")) {
            path = imgPath.substring(1);
        } else {
            path = imgPath;
        }
        return StrUtils.plusString(HttpConstants.INSTANCE.getBASE_URL(), path, "&appkey=", HttpConstants.INSTANCE.getAPPKEY(), "&sign=", HttpConstants.INSTANCE.getSIGN());
    }

    /**
     * 拼接头像地址
     * @param id
     * @return
     */
    public static String getAvatar(int id) {
        String s=StrUtils.plusString(HttpConstants.INSTANCE.getAUTHOR_BASE_URL(), "v1/getAvatar?id=", String.valueOf(id), "&appkey=",  HttpConstants.INSTANCE.getAPPKEY(), "&sign=" +  HttpConstants.INSTANCE.getSIGN());
        return s;
    }
}
