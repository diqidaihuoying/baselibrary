package wt.cn.com.wtlibrary.util;

import android.os.Environment;

import java.io.File;

/**
 * 创建日期：2018/6/12 on 17:13
 * 描述:  定义 app 的图片等路径
 * 作者:wantao
 */
public class StorageFileUtil {

    /**
     * 工程存储文件夹
     */
    private static String BASE_PROJECT_DIR = Environment.getExternalStorageDirectory()
            + File.separator + File.separator + AppInfoUtil.getAppProcessName();

    /**
     * 缓存图片文件夹
     */
    private static final String imageCache = BASE_PROJECT_DIR + "/imageCache/";


    /**
     * 获取图片的缓存路径
     * @return
     */
    public static String getImageCache() {
        File file = new File(imageCache);
        if (!file.exists()) {
            file.mkdir();
        }
        return imageCache;
    }

}

