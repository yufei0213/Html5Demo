package io.ubt.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.RelativeLayout;

import io.ubt.app.R;
import io.ubt.app.utils.Constants;
import io.ubt.app.utils.WebViewPage;
import io.ubt.app.view.component.MenuBarView;
import io.ubt.app.view.component.TitleBarView;

/**
 * Created by wangyufei on 16/10/22.
 */

public class MainActivity extends BaseActivity {

    private TitleBarView titleBarView; //默认不显示，此框架中不实现标题栏，根据项目进行定制

    private WebViewPage firstPage;
    private WebViewPage secondPage;
    private WebViewPage thirdPage;

    private WebViewPage activePage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //设置状态栏样式
        setStatusBar();
        //初始化标题栏
        initTitleBar();
        //初始化底部菜单栏
        initMenuBar();
    }

    public static Intent createIntent(Context context) {

        return new Intent(context, MainActivity.class);
    }

    private void initTitleBar() {

        titleBarView = (TitleBarView) this.findViewById(R.id.title_bar);
        titleBarView.setOnBtnClickListener(onTitleBarBtnClickListener);
    }

    private void initMenuBar() {

        MenuBarView menuBarView = (MenuBarView) this.findViewById(R.id.menu_bar);
        menuBarView.setOnItemClickListener(onMenuBarItemClickListener);

        menuBarView.clickFistItem(); //默认选中第一个
    }

    private WebViewPage createWebView(String url) {

        WebViewPage webViewPage = new WebViewPage(this, null);
        webViewPage.loadUrl(url);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        ((RelativeLayout) findViewById(R.id.web_view)).addView(webViewPage, params);

        return webViewPage;
    }

    private void showFirstPage() {

        if (firstPage == null)
            firstPage = createWebView(Constants.FIRST_PAGE);
        else
            firstPage.onReload();

        activePage = firstPage;
        activePage.show();
    }

    private void showSecondPage() {

        if (secondPage == null)
            secondPage = createWebView(Constants.SECOND_PAGE);
        else
            secondPage.onReload();

        activePage = secondPage;
        activePage.show();
    }

    private void showThirdPage() {

        if (thirdPage == null)
            thirdPage = createWebView(Constants.THIRD_PAGE);
        else
            thirdPage.onReload();

        activePage = thirdPage;
        activePage.show();
    }

    private TitleBarView.OnBtnClickListener onTitleBarBtnClickListener = new TitleBarView.OnBtnClickListener() {
        @Override
        public void leftBtnClick() {

            // TODO: 16/10/24  
        }

        @Override
        public void rightBtnCLick() {

            // TODO: 16/10/24  
        }
    };

    private MenuBarView.OnItemClickListener onMenuBarItemClickListener = new MenuBarView.OnItemClickListener() {
        @Override
        public void onMenuItemClick(int flag) {

            if (activePage != null)
                activePage.hide();

            switch (flag) {
                case 1:
                    showFirstPage();
                    break;
                case 2:
                    showSecondPage();
                    break;
                case 3:
                    showThirdPage();
                    break;
            }
        }
    };
}
