package cn.com.base.util;

import android.content.SharedPreferences;

import cn.com.base.LibApplication;


public class ThemeUtil {

    private final String THEME_MODE = "theme_mode";

    private final SharedPreferences.Editor mEditor;
    private final SharedPreferences mSp;
    private static ThemeUtil mCappuSP;


    public static ThemeUtil getInstance() {
        if (mCappuSP == null) {
            mCappuSP = new ThemeUtil();
        }

        return mCappuSP;
    }

    public ThemeUtil() {
        mSp = LibApplication.application.getSharedPreferences("ThemePreferences", 0);
        mEditor = mSp.edit();
    }

    /*--------------- 读 ---------------*/

    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    /**
     * 读取整型
     */
    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    /**
     * 读取long
     */
    public long getLong(String key, long defValue) {
        return mSp.getLong(key, defValue);
    }

    /**
     * 读取布尔型
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }


    /*--------------- 写 ---------------*/
    public void putString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * 写入int
     *
     * @param key
     * @param value
     */
    public void putInt(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    /**
     * 写入long
     *
     * @param key
     * @param value
     */
    public void putLong(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    /**
     * 写入boolean
     *
     * @param key
     * @param value
     */
    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    /**
     * 是不是默认的主题
     * 0 默认主题
     * 1 夜间主题
     * @return
     */
    public boolean isDefaultTheme() {
        int anInt = getInt(THEME_MODE, 0);
        return anInt == 0 ? true : false;
    }

    /**
     * 设置默认主题
     */
    public void setDefaultTheme()
    {
        putInt(THEME_MODE,0);
    }

    /**
     * 设置夜间主题
     */
    public void setNightTheme()
    {
        putInt(THEME_MODE,1);
    }
}
