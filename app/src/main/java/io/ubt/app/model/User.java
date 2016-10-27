package io.ubt.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangyufei on 16/10/20.
 */

public class User {

    private int id;
    private String name;

    private String accessToken;

    public User() {
    }

    public User(JSONObject object) {

        this.id = object.optInt("id");
        this.name = object.optString("name");
        this.accessToken = object.optString("accessToken");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {

        JSONObject json = new JSONObject();
        try {

            json.put("id", id);
            json.put("name", name);
            json.put("accessToken", accessToken);
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return json.toString();
    }
}
