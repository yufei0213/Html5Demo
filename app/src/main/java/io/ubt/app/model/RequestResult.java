package io.ubt.app.model;

import org.json.JSONObject;

/**
 * Created by wangyufei on 16/10/27.
 */

public class RequestResult {

    private int code;
    private String msg;
    private String date;
    private String time;

    private JSONObject result;

    public RequestResult(JSONObject object) {

        this.code = object.optInt("code");
        this.msg = object.optString("msg");
        this.date = object.optString("date");
        this.time = object.optString("time");

        this.result = object.optJSONObject("result");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
}
