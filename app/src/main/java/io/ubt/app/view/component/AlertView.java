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

    private AlertDialog alertDialog;
    private Button alertOkBtn;
    private Button alertLeftBtn;
    private Button alertRightBtn;
    private LinearLayout btnLayout;
    private TextView alertMessage;

    public AlertView(Context context) {

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.setInverseBackgroundForced(true);

        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(R.layout.alert_view);

        alertOkBtn = (Button) window.findViewById(R.id.alert_ok_btn);
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

    public void setPositiveButton(String text, View.OnClickListener listener) {

        btnLayout.setVisibility(View.INVISIBLE);
        alertOkBtn.setVisibility(View.VISIBLE);
        alertOkBtn.setText(text);
        alertOkBtn.setOnClickListener(listener);
    }

    public void setLeftButton(String text, View.OnClickListener listener) {

        btnLayout.setVisibility(View.VISIBLE);
        alertOkBtn.setVisibility(View.INVISIBLE);
        alertLeftBtn.setText(text);
        alertLeftBtn.setOnClickListener(listener);
    }

    public void setRightButton(String text, View.OnClickListener listener) {

        alertRightBtn.setText(text);
        alertRightBtn.setOnClickListener(listener);
    }

    public void dismiss() {

        alertDialog.dismiss();
    }
}
