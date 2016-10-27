package io.ubt.app.model;

/**
 * Created by wangyufei on 16/10/27.
 */

public enum DataType {

    USERID(0, "userId"),
    USERNAME(1, "username"),
    PASSWORD(2, "password"),
    ACCESSTOKEN(3, "accessToken"),
    SERVICE_DATE(4, "service_date");

    private int id;
    private String type;

    DataType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
