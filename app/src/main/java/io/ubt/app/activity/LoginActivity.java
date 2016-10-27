package io.ubt.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import io.ubt.app.R;
import io.ubt.app.utils.UserHelper;

/**
 * Created by wangyufei on 16/10/22.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    public static Intent createIntent(Context context) {

        return new Intent(context, LoginActivity.class);
    }

    private void login(String username, String password) {

        if (!validateUserName(username) || !validatePassWord(password))
            return;

        showLoading(); //开启loading
        UserHelper.login(username,
                password,
                new UserHelper.LoginSuccess() {
                    @Override
                    public void execute() {

                        hideLoading(); //隐藏loading
                        startActivity(MainActivity.createIntent(LoginActivity.this));
                    }
                }, new UserHelper.LoginFailure() {
                    @Override
                    public void execute(int code, String msg) {

                        hideLoading(); //隐藏loading
                        showAlert(msg); //提示失败信息
                    }
                });
    }

    private boolean validateUserName(String username) {

        if (username == null || username.equals("")) {

            showAlert(getResources().getString(R.string.username_null));
            return false;
        }

        String pattern = "^([a-zA-Z0-9]*)$"; //只允许字母和数字
        if (!username.matches(pattern)) {

            showAlert(getResources().getString(R.string.username_validate));
            return false;
        }

        return true;
    }

    private boolean validatePassWord(String password) {

        if (password == null || password.equals("")) {

            showAlert(getResources().getString(R.string.password_null));
            return false;
        }

        return true;
    }
}
