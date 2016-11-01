package io.ubt.app.handler;

import org.json.JSONException;
import org.json.JSONObject;

import io.ubt.app.utils.Constants;

/**
 * Created by yufei0213 on 16/11/1.
 */

public class LogoutHandler extends BaseHandler<String> {

    public LogoutHandler(HandlerListener<String> handlerListener) {

        this.requestUrl = Constants.API_URL_LOGOUT;
        this.handlerListener = handlerListener;
    }

    @Override
    protected String parseJsonObject(JSONObject json) throws JSONException {

        return null;
    }
}
