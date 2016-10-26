package io.ubt.app.utils;

/**
 * Created by wangyufei on 16/10/24.
 */

public class Constants {

    /**
     * cannot be instantiated
     */
    private Constants() {

        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * set true while developing, set false while releasing
     */
    public final static boolean isDebugModel = false;

    /**
     * api root
     */
    public final static String API_URL_ROOT = "";

    /**
     * api version
     */
    public final static String API_VERSION = "v1";

    /**
     * api url
     */
    public final static String API_URL_BASE = API_URL_ROOT + API_VERSION + "/";

    /**
     * login url
     */
    public final static String API_URL_LOGIN = API_URL_BASE + "";

    /**
     * logout url
     */
    public final static String API_URL_LOGOUT = API_URL_BASE + "";

    /**
     * static resources root path
     */
    public final static String STATIC_FILE_ROOT = "file:///android_asset/";

    public final static String FIRST_PAGE = STATIC_FILE_ROOT + "page/view/first-page.html";

    public final static String SECOND_PAGE = STATIC_FILE_ROOT + "page/view/second-page.html";

    public final static String THIRD_PAGE = STATIC_FILE_ROOT + "page/view/third-page.html";
}
