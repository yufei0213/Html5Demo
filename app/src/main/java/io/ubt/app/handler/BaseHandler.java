package io.ubt.app.handler;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.ubt.app.model.DataType;
import io.ubt.app.model.RequestResult;
import io.ubt.app.model.Status;
import io.ubt.app.utils.DataStorage;
import io.ubt.app.utils.UserHelper;

/**
 * Created by wangyufei on 16/10/27.
 *
 * 服务端返回值示例
 *{
 *  "code":0,
 *  "msg":"success",
 *  "date":"10\/27\/2016",
 *  "time":"01:36",
 *  "result":{},
 * }
 */

public abstract class BaseHandler<T> {

    protected boolean isInterrupted = false; //请求是否被中断
    protected boolean checkAccessToken = true; //是否检查accessToken

    protected String requestUrl; //请求的url
    protected T result; //请求结果

    protected HandlerListener<T> handlerListener; //请求完成的监听

    /**
     * 将json转换成需要传的对象
     *
     * @param json
     * @return
     */
    protected abstract T parseJsonObject(JSONObject json) throws JSONException;

    /**
     * 执行请求
     *
     * @param param
     */
    public void execute(final Map<String, Object> param) {

        AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams requestParams = new RequestParams();

        // 将所有传入的参数组成Http传参
        if (!param.isEmpty())
            for (Map.Entry<String, Object> entry : param.entrySet())
                requestParams.put(entry.getKey(), entry.getValue());

        // 如果该请求不是登录请求，则需要添加accessToken
        if (checkAccessToken)
            requestParams.put(DataType.ACCESSTOKEN.getType(), UserHelper.getAccessToken());

        client.get(requestUrl, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, org.apache.http.Header[] headers, byte[] responseBody) {

                if (statusCode != 200) { //请求不成功，返回未知错误

                    onFailureEvent(Status.UNKNOWN_ERROR.getCode(), Status.UNKNOWN_ERROR.getMsg());
                    return;
                }

                try { //请求成功

                    RequestResult result = new RequestResult(new JSONObject(new String(responseBody)));

                    if (result.getCode() != Status.SUCCESS.getCode()) { //请求成功，但是状态码不对

                        onFailureEvent(result.getCode(), result.getMsg());
                        return;
                    }

                    parseServerDate(result.getDate(), result.getTime());
                    onSuccessEvent(result.getResult());
                } catch (Exception e) {

                    e.printStackTrace();
                    onFailureEvent(Status.PARSE_RESULTS_ERROR.getCode(), Status.PARSE_RESULTS_ERROR.getMsg());
                }
            }

            @Override
            public void onFailure(int statusCode, org.apache.http.Header[] headers, byte[] responseBody, Throwable error) {

                error.printStackTrace();
                onFailureEvent(Status.REQUEST_TIMEOUT.getCode(), Status.REQUEST_TIMEOUT.getMsg());
            }
        });
    }

    /**
     * 中断请求
     */
    protected void interrupt() {

        this.isInterrupted = true;
    }

    /**
     * 存储服务器时间
     *
     * @param date
     * @param time
     * @throws JSONException
     */
    protected void parseServerDate(String date, String time) {

        DataStorage.getInstance().putString(DataType.SERVICE_DATE.getType(), date + " " + time);
    }

    /**
     * 成功事件监听
     *
     * @param json
     */
    protected void onSuccessEvent(JSONObject json) throws JSONException {

        if (isInterrupted)
            return;

        if (json != null)
            result = parseJsonObject(json);

        handlerListener.onSuccess(result);
    }

    /**
     * 失败事件监听
     *
     * @param code
     * @param msg
     */
    protected void onFailureEvent(int code, String msg) {

        if (isInterrupted)
            return;

        handlerListener.onFailure(code, msg);
    }
}
