$(function(){

	if (Global.isWebModel())
		Global.init();
});

/**
 * 定义页面全局命名空间
 */
var Global = function() {};

Global.isLogin = false;
Global.accessToken = null;
Global.username = null;

Global.apiUrlRoot = "http://easternbus.88towns.com/tickets_api/";
//Global.apiUrlRoot = "http://192.168.1.117:8080/";
//Global.apiUrlRoot = "http://192.168.31.232:8080/";


// ********** 调试模式 start
Global.model = Constants.MODEL_WEB;// WEB模式
//Global.model = Constants.MODEL_APP;// 运行模式
// ********** 调试模式 end

// 是否为web调试模式
Global.isWebModel = function() {

	return Global.model == Constants.MODEL_WEB;
};


/**
 * 定义原生接口对象
 */
var SDK = function() {}// 弹出框等控件的原生接口
var PAGE = function() {};// 关于页面跳转导航栏设置等方法的原生接口
var USER = function() {};// 用户登录、获取登录状态等方法的原生接口

if (!Global.isWebModel()) {

	function connectWebViewJavascriptBridge(callback) {
		if (window.WebViewJavascriptBridge) {
			callback(WebViewJavascriptBridge)
		} else {
			document.addEventListener('WebViewJavascriptBridgeReady', function() {
				callback(WebViewJavascriptBridge)
			}, false)
		}
	}

	connectWebViewJavascriptBridge(function(bridge) {

		function log(message, data) {

		}

		bridge.init(function(message, responseCallback) {

			log('JS got a message', message);
			var data = { 'Javascript Responds':'Wee!' };
			log('JS responding with', data);
			//responseCallback(data);
		});

		/**
		 * 打印日志
		 * @param msg
		 */
		SDK.log = function(msg) {

			bridge.callHandler('log', msg);
		}

		/**
		 * 登陆
		 * name:用户名
		 * password:密码
		 * callback(result):回调, result:0成功，1用户名不存在，2密码错误，3用户未激活
		 */
		USER.login = function(name, password, callback){

			var data = {'name':name, 'password':password};

			bridge.callHandler('login', data, function(response){
				//eval(callback+'('+response+')');
				callback(response);
			});
		};

		/**
		 * 有则返回字符串，没有则返回null
		 */
		USER.getAccessToken = function(){

			return Global.accessToken;
		};

		/**
		 * 返回true or false
		 */
		USER.isLogin = function(){

			return Global.isLogin;
		};

		/**
		 * 退出
		 * callBack(result) 0:成功，1：失败
		 */
		USER.logout = function(callBack){

			Global.isLogin = false;
			bridge.callHandler('logout');
			callBack();
		};

		/**
		 * 打开页面
		 * url:新页面地址
		 * param:传递的参数
		 */
		PAGE.openPage = function(url, param){

			var data = {'url':url, 'param':param};
			bridge.callHandler('openPage', data);
		};

		/**
		 * 打开页面
		 */
		PAGE.openModelPage = function(url, param){

			var data = {'url':url, 'param':param};
			bridge.callHandler('openModelPage', data);
		};

		/**
		 * 返回到上一个页面
		 */
		PAGE.back = function(index){

			if(!index)
				index = -1

			bridge.callHandler('back', index);
		};

		PAGE.showLoading = function(){

			bridge.callHandler('showLoading');
		};

		PAGE.closeLoading = function(){

			bridge.callHandler('closeLoading');
		};

		/**
		 * 显示键盘
		 */
		SDK.showSoftInput = function(){

		};

		/**
		 * 隐藏键盘
		 */
		SDK.hideSoftInput = function(){

		};

		SDK.alert = function(msg, callBack) {

			bridge.callHandler('alert', msg, function(response){
				callBack(response);
			});
		};

		SDK.confirm = function(msg, okCallBack, cancelCallBack) {

			bridge.callHandler('confirm', msg, function(response){
				if(response == '1'){
					okCallBack();
				}else{
					cancelCallBack();
				}
			});
		};
	});
} else {

	/**
	 * 登陆
	 * name:用户名
	 * password:密码
	 * callback(result):回调, result:0成功，1用户名不存在，2密码错误，3用户未激活
	 */
	USER.login = function(name, password, callback){

		console.log("login");
	};

	/**
	 * 有则返回字符串，没有则返回null
	 */
	USER.getAccessToken = function(){

		var accessToken = null;

		return accessToken;
	};

	/**
	 * 返回true or false
	 */
	USER.isLogin = function(){

		return true;
	};

	/**
	 * 退出
	 * callBack(result) 0:成功，1：失败
	 */
	USER.logout = function(callBack){
		callBack();
	};

	/**
	 * 打开页面
	 * url:新页面地址
	 * param:传递的参数
	 */
	PAGE.openPage = function(url, param){

		window.location.href= url.split("/")[url.split("/").length-1]+"?type="+param;
	};

	/**
	 * 打开页面
	 */
	PAGE.openModelPage = function(url, param){

		window.location.href= url.split("/")[url.split("/").length-1]+"?type="+param;
	};

	/**
	 * 返回到上一个页面
	 */
	PAGE.back = function(index){

		if(index)
			window.history.go(index);
		else
			window.history.go(-1);
	};

	/**
	 * 显示键盘
	 */
	SDK.showSoftInput = function(){

	};

	/**
	 * 隐藏键盘
	 */
	SDK.hideSoftInput = function(){

	};

	SDK.alert = function(msg, callBack) {

		var r = window.confirm( msg);
		if(r){
			callBack();
		}
	};

	SDK.alert = function(msg) {

		alert(msg);
	};

	SDK.confirm = function(msg, okCallBack) {

		var r = window.confirm( msg);
		if(r){
			okCallBack();
		}
	};

	SDK.confirm = function(msg, okCallBack, cancelCallBack) {

		var r = window.confirm( msg);
		if(r){
			okCallBack();
		}else{
			cancelCallBack();
		}
	};
}