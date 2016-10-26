package io.ubt.app.view.component;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.ubt.app.R;

/**
 * Created by wangyufei on 16/10/24.
 */

public class ToastView {

    public static Toast makeText(Context context, String text, int duration) {

        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.toast_view, null);

        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(duration == 1 ? duration : Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);

        TextView textView = (TextView) view.findViewById(R.id.toast_text);
        textView.setText(text);

        return toast;
    }
}
