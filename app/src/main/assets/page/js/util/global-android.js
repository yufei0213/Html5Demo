// 全局变量
var Global = function() {};

var SDK = window.sdk; // 弹出框等控件的原生接口
var PAGE = window.page; // 关于页面跳转导航栏设置等方法的原生接口
var USER = window.user; // 用户登录、获取登录状态等方法的原生接口

Global.apiUrlRoot = "http://test.www.ilikebus.com/app/";

// ********** 调试模式 start
Global.model = Constants.MODEL_WEB;// WEB模式
//Global.model = Constants.MODEL_APP;// 运行模式
// ********** 调试模式 end

// 是否为web调试模式
Global.isWebModel = function() {

	return Global.model == Constants.MODEL_APP;
};

if (Global.isWebModel()) {

	SDK = function() {};
	PAGE = function() {};
	USER = function() {};
	
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
	 * 返回到上一个页面
	 */
	PAGE.back = function(){
		
		window.history.back(-1);
	};
	
	/**
	 * 重写手机back按钮
	 * methodName回调
	 */
	PAGE.overrideBackMethod = function(methodName){
		
	};
	
	/**
	 * 设置导航栏标题
	 */
	PAGE.setTitleWithText = function(text){
		
	};
	
	/**
	 * 设置导航栏右侧按钮
	 * text:按钮文本
	 */
	PAGE.setRightBtnWithText = function(text){
		console.log("setRightBtnWithText:" + title)
	};
	
	/**
	 * 设置导航栏左侧按钮
	 * text:按钮文本
	 */
	PAGE.setLeftBtnWithText = function(text){
		console.log("setLeftBtnWithText:" + title)
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
			eval(callBack+'()');
		}
	};
	
	SDK.alert = function(msg) {
		
		alert(msg);
	};
	
	SDK.confirm = function(msg, okCallBack) {
		
		var r = window.confirm( msg);
		if(r){
			eval(okCallBack+'()');
		}
	};
	
	SDK.confirm = function(msg, okCallBack, cancelCallBack) {
		
		var r = window.confirm( msg);
		if(r){
			eval(okCallBack+'()');
		}else{
			eval(cancelCallBack+'()');
		}
	};
};

