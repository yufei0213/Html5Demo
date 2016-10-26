package io.ubt.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import io.ubt.app.R;
import io.ubt.app.utils.StatusBarHelper;
import io.ubt.app.view.component.AlertView;
import io.ubt.app.view.component.LoadingView;

/**
 * Created by wangyufei on 16/10/19.
 */

public class BaseActivity extends FragmentActivity {

    protected static final String EXTRA_ANIM_IN = "io.ubt.app.activity.BaseActivity.anim_in";
    protected static final String EXTRA_ANIM_OUT = "io.ubt.app.activity.BaseActivity.anim_in";

    private int animInIndex;
    private int animOutIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        doAnimate();
    }

    @Override
    protected void onPause() {

        super.onPause();

        this.animation(animOutIndex == 0 ? animOutIndex : animOutIndex - 1,
                animInIndex == 0 ? animInIndex : animInIndex + 1);
    }

    protected void showLoading() {

        LoadingView.showLoading(this, (ViewGroup) this.findViewById(R.id.container));
    }

    protected void showLoading(String text) {

        LoadingView.showLoading(this, (ViewGroup) this.findViewById(R.id.container), text);
    }

    protected void showLoading(LoadingView.OnCancelBtnClickListener listener) {

        LoadingView.showLoading(this, (ViewGroup) this.findViewById(R.id.container), listener);
    }

    protected void showLoading(String text, LoadingView.OnCancelBtnClickListener listener) {

        LoadingView.showLoading(this, (ViewGroup) this.findViewById(R.id.container), text, listener);
    }

    protected void hideLoading() {

        LoadingView.hideLoading(this);
    }

    protected void showAlert(String msg) {

        final AlertView alertView = new AlertView(this);

        alertView.setMessage(msg);
        alertView.setPositiveButton("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertView.dismiss();
            }
        });
    }

    protected void showConfirm(String msg) {

        final AlertView alertView = new AlertView(this);

        alertView.setMessage(msg);
        alertView.setRightButton("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertView.dismiss();
            }
        });
        alertView.setLeftButton("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertView.dismiss();
            }
        });
    }

    protected void setStatusBar() {

        if (StatusBarHelper.canTransparent()) {

            StatusBarHelper.setTranslationStatusBar(this);
            StatusBarHelper.translationY(this, (ViewGroup) this.findViewById(R.id.content));
        }
    }

    protected void setStatusBar(int colorId) {

        if (StatusBarHelper.canTransparent()) {

            StatusBarHelper.setTranslationStatusBar(this);
            StatusBarHelper.translationY(this, (ViewGroup) this.findViewById(R.id.content), getResources().getColor(colorId));
        }
    }

    protected void setStatusBar(int viewId, int colorId) {

        if (StatusBarHelper.canTransparent()) {

            StatusBarHelper.setTranslationStatusBar(this);
            StatusBarHelper.translationY(this, (ViewGroup) this.findViewById(viewId), getResources().getColor(colorId));
        }
    }

    protected void doAnimate() {

        animInIndex = getIntent().getIntExtra(EXTRA_ANIM_IN, 3);
        animOutIndex = getIntent().getIntExtra(EXTRA_ANIM_OUT, 2);

        this.animation(animInIndex, animOutIndex);
    }

    protected void animation(int animInIndex, int animOutIndex) {

        this.overridePendingTransition(getAnimation(animInIndex), getAnimation(animOutIndex));
    }

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
