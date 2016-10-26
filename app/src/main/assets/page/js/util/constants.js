/**
 * 常量
 */
var Constants = function () {

};

// 运行模式
Constants.MODEL_APP = "app"; // APP正式模式
Constants.MODEL_WEB = "web"; // 页面调试模式


// 检票结果类型
Constants.CHECK_IN_RESULT_UNKNOW = 0;
Constants.CHECK_IN_RESULT_VALID = 1;
Constants.CHECK_IN_RESULT_USED = 2;
Constants.CHECK_IN_RESULT_EXPIRED = 3;
Constants.CHECK_IN_RESULT_UNMATCHED_ROUTE = 4;
Constants.CHECK_IN_RESULT_UNMATCHED_DATE = 5;
Constants.CHECK_IN_RESULT_UNMATCHED_TIME = 6;
Constants.CHECK_IN_RESULT_INVALID = 7;

Constants.STATUS_NO_PRINT = 0;// 未打印
Constants.TICKET_STATUS_NORMAL = 1;// 正常
Constants.TICKET_STATUS_USED = 2;// 已经检票
Constants.STATUS_RECYLE = 3;//作废
Constants.STATUS_ERROR = 4;//故障票
Constants.TICKET_STATUS_RESCHEDULE = 11;
Constants.TICKET_STATUS_REFUND = 12;

// 1：open ticket，11：eastern，21：ilikebus，22：gotobus
Constants.ORIGIN_OPEN_TICKET = 1;



// 页面路径
Constants.PAGE_URL_LOGIN = "/html/login.html";

Constants.PAGE_URL_SELECT_SCHEDULE = "/html/select-schedule.html";
Constants.PAGE_URL_ROUTE_SELECTOR = "/html/route-selector.html";
Constants.PAGE_URL_DATE_SELECTOR = "/html/date-selector.html";
Constants.PAGE_URL_LOCATION_SELECTOR = "/html/location-selector.html";

Constants.PAGE_URL_CHECK_IN = "/html/check-in.html";
Constants.PAGE_URL_CHECK_RESULT = "/html/check-result.html";
Constants.PAGE_URL_CHECK_TICKET = "/html/ticket-detail.html";

Constants.PAGE_URL_RESCHEDULE = "/html/reschedule.html";
Constants.PAGE_URL_RESCHEDULE_SWIPE = "/html/reschedule-swipe.html";
Constants.PAGE_URL_RESCHEDULE_CONFIRM = "/html/reschedule-confirm.html";
Constants.PAGE_URL_RESCHEDULE_RESULT = "/html/reschedule-result.html";

Constants.PAGE_URL_PAYMENT = "/html/payment.html";
Constants.PAGE_URL_PAYMENT_SWIPE = "/html/payment-swipe.html";
Constants.PAGE_URL_PAYMENT_CONFIRM = "/html/payment-confirm.html";
Constants.PAGE_URL_PAYMENT_RESULT = "/html/payment-result.html";

Constants.PAGE_URL_MORE = "/html/more.html";


// schedule相关
Constants.LS_KEY_SCHEDULE_DEPARTURE_DATE = "schedule-departure-date";
Constants.LS_KEY_SCHEDULE_ROUTE_ID = "schedule-route-id";
Constants.LS_KEY_SCHEDULE_ROUTE = "schedule-route";
Constants.LS_KEY_SCHEDULE_LOCATION_ID = "schedule-check-location-id";
Constants.LS_KEY_SCHEDULE_LOCATION = "schedule-check-location";
Constants.LS_KEY_SCHEDULE_ACTION="schedule-action";
Constants.LS_KEY_SCHEDULE_CURRENT_DATE="schedule-current-date";

Constants.LS_KEY_SCHEDULE_ROUTE_CACHE = "schedule-route-cache";
Constants.LS_KEY_SCHEDULE_LOCATION_CACHE = "schedule-location-cache";

Constants.LS_KEY_CHECK_IN_ACTION="check-in-action";