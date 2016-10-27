package io.ubt.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangyufei on 16/10/27.
 */

public class DataStorage {

    private static Context context;
    private static String Tag = null;
    private static DataStorage dataStorage = null;

    public static DataStorage getInstance() {

        if (dataStorage == null)
            dataStorage = new DataStorage();

        if (context == null)
            context = ContextApplication.getContext();

        if (Tag == null)
            Tag = AppUtil.getAppName(context) + "_data";

        return dataStorage;
    }

    /**
     * 持久化数据
     */
    public void putString(String key, String value) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putInt(String key, int value) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void putFloat(String key, float value) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void putLong(String key, long value) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 获取持久化数据
     */
    public String getString(String key) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        return settings.getString(key, "");
    }

    public boolean getBoolean(String key) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        return settings.getBoolean(key, false);
    }

    public int getInt(String key) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        return settings.getInt(key, 0);
    }

    public float getFloat(String key) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        return settings.getFloat(key, 0f);
    }

    public long getLong(String key) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        return settings.getLong(key, 0l);
    }

    /**
     * 删除持久化数据
     */
    public void remove(String key) {

        SharedPreferences settings = context.getSharedPreferences(Tag, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }
}
