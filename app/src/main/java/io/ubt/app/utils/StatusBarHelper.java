package io.ubt.app.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import io.ubt.app.R;

/**
 * Created by wangyufei on 16/10/24.
 */

public class StatusBarHelper {

    private static String version_release = Build.VERSION.RELEASE;
    private static int version_sdk = Build.VERSION.SDK_INT;

    public static void setTranslationStatusBar(Activity context) {

        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    //返回true则需要向下平移
    public static boolean canTransparent() {

        int release = version_sdk;
        return release >= 19;
    }

    public static View translationY(Context context, ViewGroup view) {

        int height = AppUtil.getStatusBarHeight(context);
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        View block = new View(context);
        block.setBackgroundColor(context.getResources().getColor(R.color.statusbar_bg));
        view.addView(block, 0, param);

        return block;
    }

    public static View translationY(Context context, ViewGroup view, int color) {

        int height = AppUtil.getStatusBarHeight(context);
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);

        View block = new View(context);
        block.setBackgroundColor(color);
        view.addView(block, 0, param);

        return block;
    }
}
