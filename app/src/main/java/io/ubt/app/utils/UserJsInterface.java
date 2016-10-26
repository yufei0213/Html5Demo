package io.ubt.app.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by wangyufei on 16/10/20.
 */

public class UserJsInterface {

    private Context context;
    private WebViewPage webViewPage;

    public UserJsInterface(Context context, WebViewPage webViewPage) {

        this.context = context;
        this.webViewPage = webViewPage;
    }

    @JavascriptInterface
    public void login(String username, String password, String jsCallBack) {

    }

    @JavascriptInterface
    public void logout() {

    }

    @JavascriptInterface
    public void saveUser() {

    }

    @JavascriptInterface
    public void getUser() {

    }
}
