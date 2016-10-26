// 页面初始化
Global.init = function () {

    $('#btn').bind('click', function() {

        var param = {"hidden": 0,
                    "leftBtnType":0,
                    "leftBtnText": "left",
                    "rightBtnType":0,
                    "rightBtnText": "right",
                    "title":"Second Page",
                    "param": {}};

        PAGE.openPage("view/test-page.html", JSON.stringify(param));
    });
};

// 页面初始化，带参数
//Global.init = function (param) {
//
//};

// 页面重新加载时执行
Global.onReload = function () {

};

// 清除本页面的临时数据
Global.clearSession = function () {

};

// 点击页面title bar左侧按钮时执行，左侧按钮为返回按钮时不会被调用
Global.onLeftBtnClick = function() {

};

// 点击页面title bar右侧按钮时执行
Global.onRightBtnClick = function() {

};
