package io.ubt.app.activity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import io.ubt.app.R;
import io.ubt.app.utils.ActivityCollector;
import io.ubt.app.utils.AppUtil;
import io.ubt.app.utils.StatusBarHelper;
import io.ubt.app.view.component.AlertView;
import io.ubt.app.view.component.LoadingView;

/**
 * Created by wangyufei on 16/10/19.
 */

public class BaseActivity extends AppCompatActivity {

    protected static final String EXTRA_ANIM_IN = "io.ubt.app.activity.BaseActivity.anim_in";
    protected static final String EXTRA_ANIM_OUT = "io.ubt.app.activity.BaseActivity.anim_in";

    private int PERMISSION_REQUEST_CODE;
    private String PERMISSION_MSG;

    private int animInIndex;
    private int animOutIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        doAnimate(); //activity载入动画
        ActivityCollector.addActivity(this); //将activity加入activity列表中存储
    }

    @Override
    protected void onPause() {

        super.onPause();

        //activity退出动画
        this.animation(animOutIndex == 0 ? animOutIndex : animOutIndex - 1,
                animInIndex == 0 ? animInIndex : animInIndex + 1);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        ActivityCollector.removeActivity(this); //将activity从activity列表中移除
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {

            if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                onPermissonDenied(PERMISSION_MSG, PERMISSION_REQUEST_CODE); //获取权限失败的回调
            else
                onPermissionGranted(PERMISSION_MSG, PERMISSION_REQUEST_CODE); //获取到权限的回调

            return;
        }
    }

    /**
     * 检查权限
     *
     * @param permission
     * @param msg
     * @param requestCode
     */
    protected void checkPerission(String permission, String msg, int requestCode) {

        PERMISSION_MSG = msg;
        PERMISSION_REQUEST_CODE = requestCode;

        if (Build.VERSION.SDK_INT < 23) {

            if (!AppUtil.checkPermission(this, permission))
                onPermissonDenied(PERMISSION_MSG, PERMISSION_REQUEST_CODE); //获取权限失败的回调
            else
                onPermissionGranted(PERMISSION_MSG, PERMISSION_REQUEST_CODE); //获取到权限的回调
        } else {

            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission))
                    onPermissonDenied(PERMISSION_MSG, PERMISSION_REQUEST_CODE); //获取权限失败的回调
                else
                    ActivityCompat.requestPermissions(this, new String[]{permission}, PERMISSION_REQUEST_CODE); //请求权限
            } else {

                onPermissionGranted(PERMISSION_MSG, PERMISSION_REQUEST_CODE); //获取到权限的回调
            }
        }
    }

    /**
     * 获取到权限的回调
     */
    protected void onPermissionGranted(String msg, int requestCode) {
    }

    /**
     * 获取权限失败的回调
     */
    protected void onPermissonDenied(String msg, int requestCode) {
    }

    /**
     * 开启加载
     */
    protected void showLoading() {

        LoadingView.showLoading(this, (ViewGroup) this.findViewById(R.id.container));
    }

    /**
     * 开启加载
     * 可设置提示语
     */
    protected void showLoading(String text) {

        LoadingView.showLoading(this, (ViewGroup) this.findViewById(R.id.container), text);
    }

    /**
     * 开启加载
     * 可设置取消加载的回调
     */
    protected void showLoading(LoadingView.OnCancelBtnClickListener listener) {

        LoadingView.showLoading(this, (ViewGroup) this.findViewById(R.id.container), listener);
    }

    /**
     * 开启加载
     * 可设置提示语和取消加载的回调
     */
    protected void showLoading(String text, LoadingView.OnCancelBtnClickListener listener) {

        LoadingView.showLoading(this, (ViewGroup) this.findViewById(R.id.container), text, listener);
    }

    /**
     * 关闭加载
     */
    protected void hideLoading() {

        LoadingView.hideLoading(this);
    }

    /**
     * 信息提示
     * 没有回调
     *
     * @param msg
     */
    protected void showAlert(String msg) {

        if (isFinishing())
            return;

        final AlertView alertView = new AlertView(this);

        alertView.setMessage(msg);
        alertView.setPositiveButton(getResources().getString(R.string.OK), null);
    }

    /**
     * 信息提示
     * 有回调
     *
     * @param msg
     * @param listener
     */
    protected void showAlert(String msg, AlertView.OnPositiveButtonClickListener listener) {

        if (isFinishing())
            return;

        final AlertView alertView = new AlertView(this);

        alertView.setMessage(msg);
        alertView.setPositiveButton(getResources().getString(R.string.OK), listener);
    }

    /**
     * 确认提示
     * 确认有回调，取消没有回调
     *
     * @param msg
     * @param listener
     */
    protected void showConfirm(String msg, AlertView.OnRightButtonClickListener listener) {

        if (isFinishing())
            return;

        final AlertView alertView = new AlertView(this);

        alertView.setMessage(msg);
        alertView.setLeftButton(getResources().getString(R.string.Cancel), null);
        alertView.setRightButton(getResources().getString(R.string.OK), listener);
    }

    /**
     * 确认提示
     * 确认或取消都有回调
     *
     * @param msg
     * @param leftButtonClickListener
     * @param rightButtonClickListener
     */
    protected void showConfirm(String msg, AlertView.OnLeftButtonClickListener leftButtonClickListener, AlertView.OnRightButtonClickListener rightButtonClickListener) {

        if (isFinishing())
            return;

        final AlertView alertView = new AlertView(this);

        alertView.setMessage(msg);
        alertView.setLeftButton(getResources().getString(R.string.Cancel), leftButtonClickListener);
        alertView.setRightButton(getResources().getString(R.string.OK), rightButtonClickListener);
    }

    /**
     * 是否删除的提示
     *
     * @param msg
     * @param listener
     */
    protected void showDelete(String msg, AlertView.OnRightButtonClickListener listener) {

        if (isFinishing())
            return;

        final AlertView alertView = new AlertView(this);

        alertView.setMessage(msg);
        alertView.setLeftButton(getResources().getString(R.string.Cancel), null);
        alertView.setRightButton(getResources().getString(R.string.delete), getResources().getColor(R.color.alert_btn_warn_text), listener);
    }

    /**
     * 设置状态栏颜色
     *
     * @return
     */
    protected View setStatusBar() {

        if (StatusBarHelper.canTransparent()) {

            StatusBarHelper.setTranslationStatusBar(this);
            return StatusBarHelper.translationY(this,
                    (ViewGroup) this.findViewById(R.id.content));
        }

        return null;
    }

    /**
     * 设置状态栏颜色
     *
     * @param colorId
     * @return
     */
    protected View setStatusBar(int colorId) {

        if (StatusBarHelper.canTransparent()) {

            StatusBarHelper.setTranslationStatusBar(this);
            return StatusBarHelper.translationY(this,
                    (ViewGroup) this.findViewById(R.id.content),
                    getResources().getColor(colorId));
        }

        return null;
    }

    /**
     * 设置状态栏颜色
     *
     * @param viewId
     * @param colorId
     * @return
     */
    protected View setStatusBar(int viewId, int colorId) {

        if (StatusBarHelper.canTransparent()) {

            StatusBarHelper.setTranslationStatusBar(this);
            return StatusBarHelper.translationY(this,
                    (ViewGroup) this.findViewById(viewId),
                    getResources().getColor(colorId));
        }

        return null;
    }

    /**
     * 页面载入动画
     */
    protected void doAnimate() {

        animInIndex = getIntent().getIntExtra(EXTRA_ANIM_IN, 3);
        animOutIndex = getIntent().getIntExtra(EXTRA_ANIM_OUT, 2);

        this.animation(animInIndex, animOutIndex);
    }

    /**
     * 页面载入和退出动画
     *
     * @param animInIndex
     * @param animOutIndex
     */
    protected void animation(int animInIndex, int animOutIndex) {

        this.overridePendingTransition(getAnimation(animInIndex), getAnimation(animOutIndex));
    }

    /**
     * 根据动画类型的枚举获取动画资源
     *
     * @param index
     * @return
     */
    protected int getAnimation(int index) {

        int anim = R.anim.slide_freeze;

        switch (index) {

            case 0:
                anim = R.anim.slide_freeze;
                break;
            case 1:
                anim = R.anim.slide_left_in;
                break;
            case 2:
                anim = R.anim.slide_left_out;
                break;
            case 3:
                anim = R.anim.slide_right_in;
                break;
            case 4:
                anim = R.anim.slide_right_out;
                break;
            case 5:
                anim = R.anim.slide_top_in;
                break;
            case 6:
                anim = R.anim.slide_top_out;
                break;
            case 7:
                anim = R.anim.slide_bottom_in;
                break;
            case 8:
                anim = R.anim.slide_bottom_out;
                break;
            case 9:
                anim = R.anim.fade_in;
                break;
            case 10:
                anim = R.anim.fade_out;
                break;
            case 11:
                anim = R.anim.rotate;
                break;
        }

        return anim;
    }

    /**
     * 动画类型枚举
     */
    protected enum AnimateType {

        SLIDE_FREEZE(0, "slide_freeze"),
        SLIDE_LEFT_IN(1, "slide_left_in"),
        SLIDE_LEFT_OUT(2, "slide_left_out"),
        SLIDE_RIGHT_IN(3, "slide_right_in"),
        SLIDE_RIGHT_OUT(4, "slide_right_out"),
        SLIDE_TOP_IN(5, "slide_top_in"),
        SLIDE_TOP_OUT(6, "slide_top_out"),
        SLIDE_BOTTOM_IN(7, "slide_bottom_in"),
        SLIDE_BOTTOM_OUT(8, "slide_bottom_out"),
        FADE_IN(9, "fade_in"),
        FADE_OUT(10, "fade_out"),
        ROTATE(11, "rotate");

        AnimateType(int id, String name) {

            this.id = id;
            this.name = name;
        }

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
