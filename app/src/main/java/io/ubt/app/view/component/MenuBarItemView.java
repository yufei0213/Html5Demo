package io.ubt.app.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import io.ubt.app.R;

/**
 * Created by wangyufei on 16/10/24.
 */

public class MenuBarItemView extends RelativeLayout {

    private Context context;

    private TypedArray typedArray;

    private ImageView imgView;
    private TextView textView;
    private ImageView msgNoticeImgView;

    private int imgNormal;
    private int imgSelected;

    public MenuBarItemView(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.menu_bar_item_view, this, true);

        initView(attrs);
    }

    private void initView(AttributeSet attrs) {

        msgNoticeImgView = (ImageView) this.findViewById(R.id.msg_notice);

        typedArray = context.obtainStyledAttributes(attrs, R.styleable.MenuBarItemView);

        //初始化文字
        String text = typedArray.getString(R.styleable.MenuBarItemView_text);
        textView = (TextView) this.findViewById(R.id.menu_item_txt);
        textView.setText(text);

        //初始化图片，默认为非选中状态
        imgView = (ImageView) this.findViewById(R.id.menu_item_img);
        imgNormal = typedArray.getResourceId(R.styleable.MenuBarItemView_normal, 0);
        imgSelected = typedArray.getResourceId(R.styleable.MenuBarItemView_selected, 0);
        imgView.setBackgroundResource(imgNormal);

        //初始化状态，默认为非选中状态
        boolean isSelected = typedArray.getBoolean(R.styleable.MenuBarItemView_isSelected, false);
        if (isSelected)
            setSelected();
        else
            cancelSelected();

        //提交
        typedArray.recycle();
    }

    public void setSelected() {

        imgView.setBackgroundResource(imgSelected);
        textView.setTextColor(context.getResources().getColor(R.color.menu_bar_text_pressed));
    }

    public void cancelSelected() {

        imgView.setBackgroundResource(imgNormal);
        textView.setTextColor(context.getResources().getColor(R.color.menu_bar_text_normal));
    }

    public void showMsgNotice() {

        msgNoticeImgView.setVisibility(View.VISIBLE);
    }

    public void hideMsgNotice() {

        msgNoticeImgView.setVisibility(View.INVISIBLE);
    }
}
