package io.ubt.app.handler;

import org.json.JSONException;
import org.json.JSONObject;

import io.ubt.app.model.User;
import io.ubt.app.utils.Constants;

/**
 * Created by wangyufei on 16/10/27.
 */

public class LoginHandler extends BaseHandler<User> {

    public LoginHandler(HandlerListener<User> loginHandlerListener) {

        checkAccessToken = false;
        requestUrl = Constants.API_URL_LOGIN;
        handlerListener = loginHandlerListener;
    }

    @Override
    protected User parseJsonObject(JSONObject json) throws JSONException {

        return new User(json);
    }
}
