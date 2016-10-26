package io.ubt.app.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import io.ubt.app.R;

/**
 * Created by wangyufei on 16/10/24.
 */

public class MenuBarView extends LinearLayout {

    private MenuBarItemView firstItem;
    private MenuBarItemView secondItm;
    private MenuBarItemView thirdItem;

    private MenuBarItemView currItem;

    private OnItemClickListener listener;

    public MenuBarView(Context context, AttributeSet attrs) {

        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.menu_bar_view, this, true);

        init();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        this.listener = listener;
    }

    private void init() {

        firstItem = (MenuBarItemView) this.findViewById(R.id.firstItem);
        secondItm = (MenuBarItemView) this.findViewById(R.id.secondItm);
        thirdItem = (MenuBarItemView) this.findViewById(R.id.thirdItem);

        currItem = firstItem;

        firstItem.setOnClickListener(onClickListener);
        secondItm.setOnClickListener(onClickListener);
        thirdItem.setOnClickListener(onClickListener);
    }

    public void clickFistItem() {

        currItem.cancelSelected();
        currItem = firstItem;
        currItem.setSelected();

        int flag = Integer.valueOf((String) currItem.getTag());
        if (listener != null)
            listener.onMenuItemClick(flag);
    }

    public void clickSecondItem() {

        currItem.cancelSelected();
        currItem = secondItm;
        currItem.setSelected();

        int flag = Integer.valueOf((String) currItem.getTag());
        if (listener != null)
            listener.onMenuItemClick(flag);
    }

    public void clickThirdItem() {

        currItem.cancelSelected();
        currItem = thirdItem;
        currItem.setSelected();

        int flag = Integer.valueOf((String) currItem.getTag());
        if (listener != null)
            listener.onMenuItemClick(flag);
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {

            currItem.cancelSelected();
            currItem = ((MenuBarItemView) view);
            currItem.setSelected();

            int flag = Integer.valueOf((String) view.getTag());
            if (listener != null)
                listener.onMenuItemClick(flag);
        }
    };

    public interface OnItemClickListener {

        void onMenuItemClick(int flag);
    }
}
