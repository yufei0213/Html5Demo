package io.ubt.app.view.page;

import android.content.Context;
import android.util.AttributeSet;

import io.ubt.app.view.component.TitleBarView;

/**
 * Created by wangyufei on 16/10/19.
 */
public class WebViewPage extends BaseWebViewPage implements TitleBarView.OnBtnClickListener {

    public WebViewPage(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    public void leftBtnClick() {

        this.loadUrl("javascript:Global.onLeftBtnClick();");
    }

    @Override
    public void rightBtnCLick() {

        this.loadUrl("javascript:Global.onRightBtnClick();");
    }
}
