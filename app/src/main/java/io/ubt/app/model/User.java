package io.ubt.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangyufei on 16/10/20.
 */

public class User {

    private int id;
    private String name;

    public User(JSONObject object) {

        this.id = object.optInt("id");
        this.name = object.optString("name");
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

    @Override
    public String toString() {

        JSONObject json = new JSONObject();
        try {

            json.put("id", id);
            json.put("name", name);
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return json.toString();
    }
}
