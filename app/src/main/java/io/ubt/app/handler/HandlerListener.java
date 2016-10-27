package io.ubt.app.handler;


/**
 * Created by wangyufei on 16/10/27.
 */

public interface HandlerListener<T> {

    void onSuccess(T result);

    void onFailure(int code, String msg);
}
