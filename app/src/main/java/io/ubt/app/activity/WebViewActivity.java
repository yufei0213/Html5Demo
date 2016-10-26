package io.ubt.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.ubt.app.R;
import io.ubt.app.model.WebViewParams;
import io.ubt.app.utils.WebViewPage;
import io.ubt.app.view.component.TitleBarView;

/**
 * Created by wangyufei on 16/10/19.
 */

public class WebViewActivity extends BaseActivity {

    public static final String EXTRA_PARAMS = "io.ubt.app.activity.WebViewActivity.params";

    private TitleBarView titleBarView;
    private WebViewPage webViewPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

        initWebViewPage();
        initTitleBarView();
    }

    @Override
    protected void onResume() {

        super.onResume();

        webViewPage.onReload();
    }

    public static Intent createIntent(Context context, WebViewParams params) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_PARAMS, params);

        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtras(bundle);

        return intent;
    }

    private WebViewParams getWebViewParams() {

        WebViewParams params = (WebViewParams) getIntent().getSerializableExtra(EXTRA_PARAMS);

        return params;
    }

    private void initWebViewPage() {

        WebViewParams params = getWebViewParams();

        webViewPage = (WebViewPage) this.findViewById(R.id.webview_page);
        webViewPage.loadUrl(params.getUrl());
    }

    private void initTitleBarView() {

        WebViewParams webViewParams = getWebViewParams();
        if (webViewParams.getParams() == null)
            return;

        WebViewParams.Params params = webViewParams.getParams();
        titleBarView = (TitleBarView) this.findViewById(R.id.title_bar);

        //是否显示TitleBarView，如果需要隐藏TitleBarView，则不再进行其他的初始化操作
        if (params.getHidden() == TitleBarView.BtnType.HIDDEN_VIEW.getId()) {

            titleBarView.hide();
            return;
        }
        if (params.getHidden() == TitleBarView.BtnType.SHOW_VIEW.getId())
            titleBarView.show();

        //添加TitleBarView的监听，需要首先初始化 webViewPage
        titleBarView.setOnBtnClickListener(webViewPage);

        //设置左侧按钮
        if (params.getLeftBtnType() == TitleBarView.BtnType.LEFT_NO_BTN.getId()) {

            titleBarView.hideLeftBtn();
        } else {

            titleBarView.showLeftBtn();
            if (params.getLeftBtnType() == TitleBarView.BtnType.LEFT_TEXT_BTN.getId())
                titleBarView.setLeftBtn(params.getLeftBtnText());
            if (params.getLeftBtnType() == TitleBarView.BtnType.LEFT_IMG_BTN.getId())
                titleBarView.setLeftBtn(R.drawable.title_bar_left_btn);
        }

        //设置右侧按钮
        if (params.getRightBtnType() == TitleBarView.BtnType.RIGHT_NO_BTN.getId()) {

            titleBarView.hideRightBtn();
        } else {

            titleBarView.showRightBtn();
            if (params.getRightBtnType() == TitleBarView.BtnType.RIGHT_TEXT_BTN.getId())
                titleBarView.setRightBtn(params.getRightBtnText());
            if (params.getRightBtnType() == TitleBarView.BtnType.RIGHT_IMG_BTN.getId())
                titleBarView.setRightBtn(R.drawable.title_bar_right_btn);
        }

        //设置标题
        if (params.getTitle() != null && !params.getTitle().isEmpty())
            titleBarView.setTitle(params.getTitle());
    }
}
