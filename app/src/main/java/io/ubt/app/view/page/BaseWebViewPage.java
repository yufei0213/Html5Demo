package io.ubt.app.view.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.reflect.Method;

import io.ubt.app.activity.WebViewActivity;
import io.ubt.app.model.WebViewParams;
import io.ubt.app.utils.Constants;
import io.ubt.app.utils.SDKJsInterface;
import io.ubt.app.utils.UserJsInterface;

/**
 * Created by yufei0213 on 16/10/31.
 */
public class BaseWebViewPage extends WebView {

    protected Context context;

    public BaseWebViewPage(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context = context;

        initWebViewParams();

        this.addJavascriptInterface(this, "page");
        this.addJavascriptInterface(new SDKJsInterface(context, this), "sdk");
        this.addJavascriptInterface(new UserJsInterface(context, this), "user");
    }

    public void show() {

        this.setVisibility(View.VISIBLE);
    }

    public void hide() {

        this.setVisibility(View.GONE);
    }

    public void onReload() {

        this.loadUrl("javascript:Global.onReload();");
    }

    @JavascriptInterface
    public void openPage(String url, String params) {

        String tmpUrl = Constants.STATIC_FILE_ROOT + "page/" + url;
        Intent intent = WebViewActivity.createIntent(context, new WebViewParams(tmpUrl, params));

        context.startActivity(intent);
    }

    protected void initWebViewParams() {

        getSettings().setTextZoom(100);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);

        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        setWebViewClient(webViewclient);
        setWebChromeClient(webChromeClient);

        boolean multiTouch = ((Activity) context).getIntent().getBooleanExtra("multi_touch", false);
        if (!multiTouch)
            setOnTouchListener(touchListener);
        setOnLongClickListener(longClickListener);

        try {

            if (Build.VERSION.SDK_INT >= 16) {

                Class<?> clazz = this.getSettings().getClass();
                Method method = clazz.getMethod("setAllowUniversalAccessFromFileURLs", boolean.class);

                if (method != null)
                    method.invoke(this.getSettings(), true);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private WebViewClient webViewclient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {

            WebViewParams webViewParams = (WebViewParams) ((Activity) context).getIntent()
                    .getSerializableExtra(WebViewActivity.EXTRA_PARAMS);

            if (webViewParams != null) { //通过PAGE.openPage(String url, String params)打开页面

                WebViewParams.Params params = webViewParams.getParams();

                if (params != null) {

                    String param = params.getParam();

                    if (param != null && !param.isEmpty())
                        BaseWebViewPage.this.loadUrl("javascript:Global.init('" + param + "');");
                    else
                        BaseWebViewPage.this.loadUrl("javascript:Global.init();");
                }
            } else { //通过XML定义页面

                BaseWebViewPage.this.loadUrl("javascript:Global.init();");
            }
        }
    };

    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

            return super.onJsAlert(view, url, message, result);
        }
    };

    private OnLongClickListener longClickListener = new OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            return true;
        }
    };

    private OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {

            int action = arg1.getAction();

            switch (action) {

                case MotionEvent.ACTION_POINTER_2_DOWN:
                case MotionEvent.ACTION_POINTER_3_DOWN:

                    return true;
            }

            return false;
        }
    };
}
