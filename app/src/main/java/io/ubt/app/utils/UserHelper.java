package io.ubt.app.utils;

import java.util.HashMap;
import java.util.Map;

import io.ubt.app.handler.HandlerListener;
import io.ubt.app.handler.LoginHandler;
import io.ubt.app.model.DataType;
import io.ubt.app.model.User;

/**
 * Created by wangyufei on 16/10/27.
 */

public class UserHelper {

    /**
     * 登陆接口
     *
     * @param username
     * @param password
     * @param loginSuccess
     * @param loginFailure
     */
    public static void login(final String username, final String password, final LoginSuccess loginSuccess, final LoginFailure loginFailure) {

        LoginHandler handler = new LoginHandler(new HandlerListener<User>() {
            @Override
            public void onSuccess(User user) {

                afterLoginSuccess(username, password, user);

                loginSuccess.execute();
            }

            @Override
            public void onFailure(int code, String msg) {

                loginFailure.execute(code, msg);
            }
        });

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        handler.execute(params);
    }

    /**
     * 自动登录
     *
     * @param loginSuccess
     * @param loginFailure
     */
    public static void autoLogin(LoginSuccess loginSuccess, LoginFailure loginFailure) {

        String username = DataStorage.getInstance().getString(DataType.USERNAME.getType());
        String password = DataStorage.getInstance().getString(DataType.PASSWORD.getType());

        login(username, password, loginSuccess, loginFailure);
    }

    /**
     * 登录成功后保存用户信息
     *
     * @param username
     * @param password
     * @param user
     */
    public static void afterLoginSuccess(String username, String password, User user) {

        DataStorage.getInstance().putString(DataType.PASSWORD.getType(), password);

        saveUser(user);
    }

    /**
     * 保存用户信息
     *
     * @param user
     */
    public static void saveUser(User user) {

        DataStorage storage = DataStorage.getInstance();

        storage.putInt(DataType.USERID.getType(), user.getId());
        storage.putString(DataType.USERNAME.getType(), user.getName());

        saveAccessToken(user.getAccessToken());
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public static User getUser() {

        User user = new User();
        DataStorage storage = DataStorage.getInstance();

        user.setId(storage.getInt(DataType.USERID.getType()));
        user.setName(storage.getString(DataType.USERNAME.getType()));
        user.setAccessToken(storage.getString(DataType.ACCESSTOKEN.getType()));

        return user;
    }

    /**
     * 推出登陆后，清空用户数据
     */
    public static void clearUser() {

        DataStorage storage = DataStorage.getInstance();

        storage.remove(DataType.USERID.getType());
        storage.remove(DataType.USERNAME.getType());
        storage.remove(DataType.PASSWORD.getType());
        storage.remove(DataType.ACCESSTOKEN.getType());
        storage.remove(DataType.SERVICE_DATE.getType());
    }

    /**
     * 保存accessToken
     *
     * @param accessToken
     */
    public static void saveAccessToken(String accessToken) {

        DataStorage.getInstance().putString(DataType.ACCESSTOKEN.getType(), accessToken);
    }

    /**
     * 获取accessToken
     *
     * @return
     */
    public static String getAccessToken() {

        return DataStorage.getInstance().getString(DataType.ACCESSTOKEN.getType());
    }

    public interface LoginSuccess {

        void execute();
    }

    public interface LoginFailure {

        void execute(int code, String msg);
    }
}
