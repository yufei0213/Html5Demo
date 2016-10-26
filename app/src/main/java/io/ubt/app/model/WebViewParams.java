package io.ubt.app.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

import io.ubt.app.view.component.TitleBarView;

/**
 * Created by wangyufei on 16/10/22.
 */

public class WebViewParams implements Serializable {

    private String url;
    private Params params;

    public WebViewParams(String url, String params) {

        this.url = url;
        this.params = new Params(params);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public class Params implements Serializable {

        private int hidden = TitleBarView.BtnType.SHOW_VIEW.getId(); //是否隐藏TitleBarView
        private int leftBtnType = TitleBarView.BtnType.LEFT_IMG_BTN.getId(); //有三种类型，详见TitleBarView.BtnType
        private int rightBtnType = TitleBarView.BtnType.RIGHT_NO_BTN.getId(); //有三种类型，详见TitleBarView.BtnType
        private String leftBtnText; //当且仅当按钮类型为TitleBarView.BtnType.LEFT_TEXT_BTN时有效
        private String rightBtnText; //当且仅当按钮类型为TitleBarView.BtnType.RIGHT_TEXT_BTN时有效
        private String title; //TitleBarView的标题，若不需要标题，将其设置为空字符串
        private String param; //供该页面javascript调用的参数，由Global.init(param)调用，需要将其转化为JSON格式方可使用

        public Params(String params) {

            JSONObject paramsObj = JSON.parseObject(params);

            if (paramsObj == null)
                return;

            hidden = paramsObj.getIntValue("hidden");
            leftBtnType = paramsObj.getIntValue("leftBtnType");
            rightBtnType = paramsObj.getIntValue("rightBtnType");
            leftBtnText = paramsObj.getString("leftBtnText");
            rightBtnText = paramsObj.getString("rightBtnText");
            title = paramsObj.getString("title");

            JSONObject object = paramsObj.getJSONObject("param");
            if (object != null && object.size() != 0)
                param = paramsObj.getJSONObject("param").toString();
        }

        public int getHidden() {
            return hidden;
        }

        public void setHidden(int hidden) {
            this.hidden = hidden;
        }

        public int getLeftBtnType() {
            return leftBtnType;
        }

        public void setLeftBtnType(int leftBtnType) {
            this.leftBtnType = leftBtnType;
        }

        public int getRightBtnType() {
            return rightBtnType;
        }

        public void setRightBtnType(int rightBtnType) {
            this.rightBtnType = rightBtnType;
        }

        public String getLeftBtnText() {
            return leftBtnText;
        }

        public void setLeftBtnText(String leftBtnText) {
            this.leftBtnText = leftBtnText;
        }

        public String getRightBtnText() {
            return rightBtnText;
        }

        public void setRightBtnText(String rightBtnText) {
            this.rightBtnText = rightBtnText;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }
}
