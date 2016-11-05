package io.ubt.app.view.component;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.ubt.app.R;

/**
 * Created by wangyufei on 16/10/24.
 */

public class AlertView {

    private Context context;

    private AlertDialog alertDialog;
    private Button alertPositiveBtn;
    private Button alertLeftBtn;
    private Button alertRightBtn;
    private LinearLayout btnLayout;
    private TextView alertMessage;

    public AlertView(Context context) {

        this.context = context;

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.setInverseBackgroundForced(true);

        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(R.layout.alert_view);

        alertPositiveBtn = (Button) window.findViewById(R.id.alert_positive_btn);
        alertMessage = (TextView) window.findViewById(R.id.alert_message);
        alertLeftBtn = (Button) window.findViewById(R.id.alert_left_btn);
        alertRightBtn = (Button) window.findViewById(R.id.alert_right_btn);
        btnLayout = (LinearLayout) window.findViewById(R.id.alert_btn_layout);
    }

    public void setCancelable(boolean cancelable) {

        alertDialog.setCancelable(cancelable);
    }

    public void setMessage(String message) {

        alertMessage.setText(message);
    }

    public void setPositiveButton(String text, final OnPositiveButtonClickListener listener) {

        btnLayout.setVisibility(View.INVISIBLE);
        alertPositiveBtn.setVisibility(View.VISIBLE);
        alertPositiveBtn.setText(text);
        alertPositiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.onPositiveButtonClick(v);

                dismiss();
            }
        });
    }

    public void setPositiveButton(String text, int colorId, final OnPositiveButtonClickListener listener) {

        btnLayout.setVisibility(View.INVISIBLE);
        alertPositiveBtn.setVisibility(View.VISIBLE);
        alertPositiveBtn.setText(text);
        alertPositiveBtn.setTextColor(context.getResources().getColor(colorId));
        alertPositiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.onPositiveButtonClick(v);

                dismiss();
            }
        });
    }

    public void setLeftButton(String text, final OnLeftButtonClickListener listener) {

        btnLayout.setVisibility(View.VISIBLE);
        alertPositiveBtn.setVisibility(View.INVISIBLE);
        alertLeftBtn.setText(text);
        alertLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.onLeftButtonClick(v);

                dismiss();
            }
        });
    }

    public void setLeftButton(String text, int colorId, final OnLeftButtonClickListener listener) {

        btnLayout.setVisibility(View.VISIBLE);
        alertPositiveBtn.setVisibility(View.INVISIBLE);
        alertLeftBtn.setText(text);
        alertLeftBtn.setTextColor(context.getResources().getColor(colorId));
        alertLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.onLeftButtonClick(v);

                dismiss();
            }
        });
    }

    public void setRightButton(String text, final OnRightButtonClickListener listener) {

        alertRightBtn.setText(text);
        alertRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.onRightButtonClick(v);

                dismiss();
            }
        });
    }

    public void setRightButton(String text, int colorId, final OnRightButtonClickListener listener) {

        alertRightBtn.setText(text);
        alertRightBtn.setTextColor(context.getResources().getColor(colorId));
        alertRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.onRightButtonClick(v);

                dismiss();
            }
        });
    }

    public void dismiss() {

        alertDialog.dismiss();
    }

    public interface OnPositiveButtonClickListener {

        void onPositiveButtonClick(View view);
    }

    public interface OnLeftButtonClickListener {

        void onLeftButtonClick(View view);
    }

    public interface OnRightButtonClickListener {

        void onRightButtonClick(View view);
    }
}
