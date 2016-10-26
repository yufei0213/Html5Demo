package io.ubt.app.view.component;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.ubt.app.R;

/**
 * Created by wangyufei on 16/10/22.
 */

public class TitleBarView extends RelativeLayout {

    private Context context;

    private OnBtnClickListener listener;

    public TitleBarView(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.title_bar_view, this, true);
    }

    public void setOnBtnClickListener(OnBtnClickListener listener) {

        this.listener = listener;
    }

    public void show() {

        this.setVisibility(VISIBLE);
    }

    public void hide() {

        this.setVisibility(GONE);
    }

    public void showLeftBtn() {

        this.findViewById(R.id.left_btn).setVisibility(VISIBLE);
    }

    public void hideLeftBtn() {

        this.findViewById(R.id.left_btn).setVisibility(GONE);
    }

    public void showTitle() {

        this.findViewById(R.id.title).setVisibility(VISIBLE);
    }

    public void hideTitle() {

        this.findViewById(R.id.title).setVisibility(GONE);
    }

    public void showRightBtn() {

        this.findViewById(R.id.right_btn).setVisibility(VISIBLE);
    }

    public void hideRightBtn() {

        this.findViewById(R.id.right_btn).setVisibility(GONE);
    }

    public void setTitle(String title) {

        TextView titleView = (TextView) this.findViewById(R.id.title);
        titleView.setText(title);
        titleView.setVisibility(VISIBLE);
    }

    public void setTitleWithText(final String text) {

        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {

                setTitle(text);
            }
        });
    }

    public void setLeftBtn(String text) {

        this.findViewById(R.id.left_btn_img).setVisibility(GONE);

        TextView leftBtnText = (TextView) this.findViewById(R.id.left_btn_text);
        leftBtnText.setText(text);
        leftBtnText.setVisibility(VISIBLE);

        this.findViewById(R.id.left_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.leftBtnClick();
            }
        });
    }

    public void setLeftBtnWithText(final String text) {

        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {

                setLeftBtn(text);
            }
        });
    }

    public void setLeftBtn(int imgId) {

        this.findViewById(R.id.left_btn_text).setVisibility(GONE);

        ImageView leftBtnImg = (ImageView) this.findViewById(R.id.left_btn_img);
        leftBtnImg.setImageResource(imgId);
        leftBtnImg.setVisibility(VISIBLE);

        this.findViewById(R.id.left_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Activity) context).finish();
            }
        });
    }

    public void setLeftBtnWithImage(final int imgId) {

        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {

                setLeftBtn(imgId);
            }
        });
    }

    public void setRightBtn(String text) {

        this.findViewById(R.id.left_btn_img).setVisibility(GONE);

        TextView rightBtnText = (TextView) this.findViewById(R.id.right_btn_text);
        rightBtnText.setText(text);
        rightBtnText.setVisibility(VISIBLE);

        this.findViewById(R.id.right_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.rightBtnCLick();
            }
        });
    }

    public void setRightBtnWithText(final String text) {

        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {

                setRightBtn(text);
            }
        });
    }

    public void setRightBtn(int imgId) {

        this.findViewById(R.id.right_btn_text).setVisibility(GONE);

        ImageView rightBtnImg = (ImageView) this.findViewById(R.id.right_btn_img);
        rightBtnImg.setImageResource(imgId);
        rightBtnImg.setVisibility(VISIBLE);

        this.findViewById(R.id.right_btn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.rightBtnCLick();
            }
        });
    }

    public void setRightBtnWithImage(final int imgId) {

        ((Activity) context).runOnUiThread(new Runnable() {

            @Override
            public void run() {

                setRightBtn(imgId);
            }
        });
    }

    public interface OnBtnClickListener {

        void leftBtnClick();

        void rightBtnCLick();
    }

    public enum BtnType {

        LEFT_TEXT_BTN(0, "left_text_btn"),
        LEFT_IMG_BTN(1, "left_img_btn"),
        LEFT_NO_BTN(2, "left_no_btn"),
        RIGHT_TEXT_BTN(0, "rihgt_text_btn"),
        RIGHT_IMG_BTN(1, "right_img_btn"),
        RIGHT_NO_BTN(2, "right_no_btn"),
        HIDDEN_VIEW(1, "hidden_title_bar"),
        SHOW_VIEW(0, "show_title_bar");

        BtnType(int id, String name) {

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
