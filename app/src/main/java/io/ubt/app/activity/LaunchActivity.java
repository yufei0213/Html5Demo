package io.ubt.app.activity;

import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.Nullable;

import io.ubt.app.R;
import io.ubt.app.utils.UserHelper;

/**
 * Created by wangyufei on 16/10/22.
 */

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity);

        checkLogin();
    }

    private void checkLogin() {

        UserHelper.autoLogin(new UserHelper.LoginSuccess() {
            @Override
            public void execute() {

                startActivity(MainActivity.createIntent(LaunchActivity.this));
                LaunchActivity.this.finish();
            }
        }, new UserHelper.LoginFailure() {
            @Override
            public void execute(int code, String msg) {

                startActivity(LoginActivity.createIntent(LaunchActivity.this));
                LaunchActivity.this.finish();
            }
        });
    }
}
