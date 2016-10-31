package io.ubt.app.utils;

import android.content.Context;
import android.webkit.JavascriptInterface;

import io.ubt.app.model.DataType;
import io.ubt.app.view.page.BaseWebViewPage;

/**
 * Created by wangyufei on 16/10/20.
 */

public class UserJsInterface {

    private Context context;
    private BaseWebViewPage webViewPage;

    public UserJsInterface(Context context, BaseWebViewPage webViewPage) {

        this.context = context;
        this.webViewPage = webViewPage;
    }

    @JavascriptInterface
    public void login(String username, String password, final String successCallBack, final String failureCallBack) {

        UserHelper.login(username, password, new UserHelper.LoginSuccess() {
            @Override
            public void execute() {

                if (successCallBack != null)
                    webViewPage.loadUrl("javascript:" + successCallBack + "();");
            }
        }, new UserHelper.LoginFailure() {
            @Override
            public void execute(int code, String msg) {

                if (failureCallBack != null)
                    webViewPage.loadUrl("javascript:" + failureCallBack + "(" + code + ", " + msg + ");");
            }
        });
    }

    @JavascriptInterface
    public void autoLogin(final String successCallBack, final String failureCallBack) {

        String username = DataStorage.getInstance().getString(DataType.USERNAME.getType());
        String password = DataStorage.getInstance().getString(DataType.PASSWORD.getType());

        login(username, password, successCallBack, failureCallBack);
    }

    @JavascriptInterface
    public void logout() {

        UserHelper.clearUser();
    }

    @JavascriptInterface
    public void saveUser() {

    }

    @JavascriptInterface
    public String getUser() {

        return UserHelper.getUser().toString();
    }
}
