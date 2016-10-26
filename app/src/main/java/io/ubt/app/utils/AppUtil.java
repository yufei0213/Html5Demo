package io.ubt.app.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by wangyufei on 16/10/20.
 */

public class AppUtil {

    /**
     * cannot be instantiated
     */
    private AppUtil() {

        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * get app name
     *
     * @param context
     * @return app name
     */
    public static String getAppName(Context context) {

        PackageManager packageManager = context.getPackageManager();
        try {

            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;

            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * get app version name
     *
     * @param context
     * @return version name
     */
    public static String getVersionName(Context context) {

        String versionName = null;

        try {

            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * get app version code
     *
     * @param context
     * @return version code
     */
    public static int getVersionCode(Context context) {

        int versionCode = 0;

        try {

            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }

        return versionCode;
    }

    /**
     * get dpr
     *
     * @param context
     * @return dpr
     */
    public static float getDpr(Context context) {

        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * get the width of screen
     *
     * @param context
     * @return screenWidth
     */
    public static int getScreenWidth(Context context) {

        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * get the height of screen widthout VirtualKey
     *
     * @param context
     * @return screenHeight
     */
    public static int getScreenHeight(Context context) {

        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * get the height of screen width VirtualKey
     *
     * @param context
     * @return screenHeight
     */
    public static int getScreenHeightWidthVirtualKey(Context context) {

        int height = 0;
        Activity activity = (Activity) context;
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();

        @SuppressWarnings("rawtypes")
        Class c;
        try {

            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            height = dm.heightPixels;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return height;
    }

    /**
     * get height of VirtualKey
     *
     * @param context
     * @return height
     */
    public static int getVirtualKeyHeight(Context context) {

        return getScreenHeightWidthVirtualKey(context) - getScreenHeight(context);
    }

    /**
     * get the height of status bar
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {

        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0)
            result = context.getResources().getDimensionPixelSize(resourceId);

        return result;
    }

    /**
     * get macAddress
     *
     * @return
     */
    public static String getMacAddress() {

        String mac = null;
        FileReader fstream = null;

        try {

            try {

                fstream = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {

                fstream = new FileReader("/sys/class/net/eth0/address");
            }

            BufferedReader in = null;

            if (fstream != null) {

                try {

                    in = new BufferedReader(fstream, 1024);
                    mac = in.readLine();
                } catch (IOException e) {

                    e.printStackTrace();
                } finally {

                    if (fstream != null) {

                        try {

                            fstream.close();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }

                    if (in != null) {

                        try {

                            in.close();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                }
            }

            return mac;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * get device_id
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {

        try {

            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = null;

            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE))
                device_id = tm.getDeviceId();

            String mac = AppUtil.getMacAddress();

            if (TextUtils.isEmpty(device_id))
                device_id = mac;

            if (TextUtils.isEmpty(device_id))
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

            return device_id;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * get device_id and mac
     *
     * @param context
     * @return {"mac":"","device_id":""}
     */
    public static String getDeviceInfo(Context context) {

        try {

            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String device_id = null;

            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE))
                device_id = tm.getDeviceId();

            String mac = null;
            FileReader fstream = null;

            try {

                fstream = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {

                fstream = new FileReader("/sys/class/net/eth0/address");
            }

            BufferedReader in = null;

            if (fstream != null) {

                try {

                    in = new BufferedReader(fstream, 1024);
                    mac = in.readLine();

                } catch (IOException e) {

                    e.printStackTrace();
                } finally {

                    if (fstream != null) {

                        try {

                            fstream.close();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }

                    if (in != null) {

                        try {

                            in.close();
                        } catch (IOException e) {

                            e.printStackTrace();
                        }
                    }
                }
            }

            json.put("mac", mac);

            if (TextUtils.isEmpty(device_id))
                device_id = mac;

            if (TextUtils.isEmpty(device_id))
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

            json.put("device_id", device_id);

            return json.toString();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * check permission
     *
     * @param context permission
     * @return boolean
     */
    public static boolean checkPermission(Context context, String permission) {

        boolean result = false;

        if (Build.VERSION.SDK_INT >= 23) {

            try {

                Class clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);

                if (rest == PackageManager.PERMISSION_GRANTED)
                    result = true;
                else
                    result = false;

            } catch (Exception e) {

                result = false;
            }
        } else {

            PackageManager pm = context.getPackageManager();

            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED)
                result = true;
        }

        return result;
    }

    /**
     * get the intent that allows you to open app setting page
     *
     * @param context
     * @return intent
     */
    public static Intent getAppDetailSettingIntent(Context context) {

        Uri packageURI = Uri.parse("package:" + context.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);

        return intent;
    }
}
