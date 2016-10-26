package io.ubt.app.model;

/**
 * Created by wangyufei on 16/10/20.
 */

public enum DataStorageType {

    USERNAME(0, "username"),
    PASSWORD(1, "password"),
    ACCESSTOKEN(2, "accessToken");

    DataStorageType(int id, String type) {

        this.id = id;
        this.type = type;
    }

    private int id;
    private String type;

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
