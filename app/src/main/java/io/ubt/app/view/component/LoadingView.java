package io.ubt.app.view.component;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.ubt.app.R;

/**
 * Created by wangyufei on 16/10/25.
 */

public class LoadingView extends RelativeLayout {

    private static ViewGroup parent = null;
    private static LoadingView loadingView = null;

    private Context context;
    private TextView text;
    private OnCancelBtnClickListener onCancelBtnClickListener;

    public LoadingView(Context context, AttributeSet attrs, final OnCancelBtnClickListener onCancelBtnClickListener) {

        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.loading_view, this, true);

        this.context = context;
        this.onCancelBtnClickListener = onCancelBtnClickListener;

        init();
    }

    public static void showLoading(final Context context, ViewGroup parent) {

        showLoading(context, parent, context.getString(R.string.loading), null);
    }

    public static void showLoading(final Context context, ViewGroup parent, final String text) {

        showLoading(context, parent, text, null);
    }

    public static void showLoading(final Context context, ViewGroup parent, final OnCancelBtnClickListener listener) {

        showLoading(context, parent, context.getString(R.string.loading), listener);
    }

    public static void showLoading(final Context context, ViewGroup parent, final String text, final OnCancelBtnClickListener listener) {

        if (LoadingView.loadingView != null)
            return;

        LoadingView.parent = parent;

        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LoadingView.loadingView = new LoadingView(context, null, listener);
                LoadingView.loadingView.setText(text);
                LoadingView.parent.addView(LoadingView.loadingView);
            }
        });
    }

    public static void hideLoading(Context context) {

        if (LoadingView.loadingView == null)
            return;

        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (LoadingView.parent == null || LoadingView.loadingView == null)
                    return;

                LoadingView.parent.removeView(LoadingView.loadingView);
                LoadingView.loadingView = null;
                LoadingView.parent = null;
            }
        });
    }

    private void init() {

        text = (TextView) findViewById(R.id.text);

        findViewById(R.id.cancel_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoadingView.hideLoading(LoadingView.this.context);

                if (onCancelBtnClickListener != null)
                    onCancelBtnClickListener.onCancelBtnClick();
            }
        });

        initAnimate();
    }

    private void initAnimate() {

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        animation.setInterpolator(linearInterpolator);
        ImageView imageView = (ImageView) findViewById(R.id.icon);
        imageView.startAnimation(animation);
    }

    private void setText(String str) {

        if (str != null && !str.isEmpty())
            text.setText(str);
    }

    public interface OnCancelBtnClickListener {

        void onCancelBtnClick();
    }
}
