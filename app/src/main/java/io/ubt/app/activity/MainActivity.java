package io.ubt.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import io.ubt.app.R;
import io.ubt.app.view.page.BaseWebViewPage;
import io.ubt.app.utils.Constants;
import io.ubt.app.view.component.MenuBarView;
import io.ubt.app.view.component.TitleBarView;

/**
 * Created by wangyufei on 16/10/22.
 */

public class MainActivity extends BaseActivity {

    private View statusBarView; //状态栏视图，需要根据不同的页面展示不同的背景色，根据项目进行定制
    private TitleBarView titleBarView; //默认不显示，此框架中不实现标题栏，根据项目进行定制

    //本demo中只展示三个子页面，根据项目进行定制
    //有title bar的页面需要声明为WebViewPage类型
    private BaseWebViewPage firstPage;
    private BaseWebViewPage secondPage;
    private BaseWebViewPage thirdPage;

    private BaseWebViewPage activePage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //设置状态栏样式
        statusBarView = setStatusBar();

        initTitleBar();
        initMenuBar();
    }

    /**
     * 创建启动该activity的intent
     *
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {

        return new Intent(context, MainActivity.class);
    }

    /**
     * 初始化title bar
     */
    private void initTitleBar() {

        titleBarView = (TitleBarView) this.findViewById(R.id.title_bar);
        titleBarView.setOnBtnClickListener(onTitleBarBtnClickListener);
    }

    /**
     * 初始化menu bar
     */
    private void initMenuBar() {

        MenuBarView menuBarView = (MenuBarView) this.findViewById(R.id.menu_bar);
        menuBarView.setOnItemClickListener(onMenuBarItemClickListener);

        menuBarView.clickFistItem(); //默认选中第一个
    }

    /**
     * 创建web view
     *
     * @param url
     * @return
     */
    private BaseWebViewPage createWebView(String url) {

        BaseWebViewPage webViewPage = new BaseWebViewPage(this, null);
        webViewPage.loadUrl(url);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        ((RelativeLayout) findViewById(R.id.web_view)).addView(webViewPage, params);

        return webViewPage;
    }

    /**
     * 显示first page
     */
    private void showFirstPage() {

        if (firstPage == null)
            firstPage = createWebView(Constants.FIRST_PAGE);
        else
            firstPage.onReload();

        statusBarView.setBackgroundColor(getResources().getColor(R.color.first_page_statusbar));

        activePage = firstPage;
        activePage.show();
    }

    /**
     * 显示second page
     */
    private void showSecondPage() {

        if (secondPage == null)
            secondPage = createWebView(Constants.SECOND_PAGE);
        else
            secondPage.onReload();

        statusBarView.setBackgroundColor(getResources().getColor(R.color.second_page_statusbar));

        activePage = secondPage;
        activePage.show();
    }

    /**
     * 显示third page
     */
    private void showThirdPage() {

        if (thirdPage == null)
            thirdPage = createWebView(Constants.THIRD_PAGE);
        else
            thirdPage.onReload();

        statusBarView.setBackgroundColor(getResources().getColor(R.color.third_page_statusbar));

        activePage = thirdPage;
        activePage.show();
    }

    /**
     * title bar监听
     */
    private TitleBarView.OnBtnClickListener onTitleBarBtnClickListener = new TitleBarView.OnBtnClickListener() {
        @Override
        public void leftBtnClick() {

            if (activePage instanceof TitleBarView.OnBtnClickListener)
                activePage.loadUrl("javascript:Global.onLeftBtnClick();");
        }

        @Override
        public void rightBtnCLick() {

            if (activePage instanceof TitleBarView.OnBtnClickListener)
                activePage.loadUrl("javascript:Global.onRightBtnClick();");
        }
    };

    /**
     * menu bar监听
     */
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
