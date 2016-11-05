package io.ubt.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import io.ubt.app.R;
import io.ubt.app.view.component.AlertView;
import io.ubt.app.view.component.LoadingView;
import io.ubt.app.view.component.ToastView;
import io.ubt.app.view.page.BaseWebViewPage;

/**
 * Created by wangyufei on 16/10/19.
 */

public class SDKJsInterface {

    private Context context;
    private BaseWebViewPage webViewPage;

    public SDKJsInterface(Context context, BaseWebViewPage webViewPage) {

        this.context = context;
        this.webViewPage = webViewPage;
    }

    @JavascriptInterface
    public void log(String tag, String msg) {

        Log.d(tag, msg);
    }

    @JavascriptInterface
    public void Toast(String msg) {

        ToastView.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void showLoading() {

        LoadingView.showLoading(context, webViewPage);
    }

    @JavascriptInterface
    public void hideLoading() {

        LoadingView.hideLoading(context);
    }

    @JavascriptInterface
    public void showSoftInput() {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(webViewPage, InputMethodManager.SHOW_IMPLICIT);
    }

    @JavascriptInterface
    public void hideSoftInput() {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(webViewPage.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @JavascriptInterface
    public void call(String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }

    @JavascriptInterface
    public String getAppName() {

        return AppUtil.getAppName(context);
    }

    @JavascriptInterface
    public String getVersionName() {

        return AppUtil.getVersionName(context);
    }

    @JavascriptInterface
    public int getVersionCode() {

        return AppUtil.getVersionCode(context);
    }

    @JavascriptInterface
    public void alert(String msg) {

        alert(msg, null);
    }

    @JavascriptInterface
    public void alert(String msg, final String jsCallBack) {

        final AlertView alertView = new AlertView(context);
        alertView.setMessage(msg);
        alertView.setPositiveButton(context.getResources().getString(R.string.OK), new AlertView.OnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(View view) {

                if (jsCallBack != null && !jsCallBack.isEmpty()) {

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            webViewPage.loadUrl("javascript:" + jsCallBack + "();");
                        }
                    });
                }
            }
        });
    }

    @JavascriptInterface
    public void confirm(String msg, String okBtnListener) {

        confirm(msg, okBtnListener, null);
    }

    @JavascriptInterface
    public void confirm(String msg, final String okBtnListener, final String cancelBtnListener) {

        final AlertView alertView = new AlertView(context);
        alertView.setMessage(msg);
        alertView.setLeftButton(context.getResources().getString(R.string.Cancel), new AlertView.OnLeftButtonClickListener() {
            @Override
            public void onLeftButtonClick(View view) {

                if (cancelBtnListener != null && !cancelBtnListener.isEmpty()) {

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            webViewPage.loadUrl("javascript:" + cancelBtnListener + "();");
                        }
                    });
                }
            }
        });
        alertView.setRightButton(context.getResources().getString(R.string.OK), new AlertView.OnRightButtonClickListener() {
            @Override
            public void onRightButtonClick(View view) {

                if (okBtnListener != null && !okBtnListener.isEmpty()) {

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            webViewPage.loadUrl("javascript:" + okBtnListener + "();");
                        }
                    });
                }
            }
        });
    }
}
