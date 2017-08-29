package com.boboyuwu.common.util;

/**
 * Created by wubo on 2017/2/13.
 */

public class RxBusEventKeys {

    //这里存放Rxbus的事件key
    public static final String FONTCOLOR_CHANGE = "product_search_fontColor_change";
    //productSearchActivity页顶部筛选栏目通知
    public static final String DATA_CHANGE = "product_search_top_filter_data_change";
    //隐藏筛选栏
    public static final String HIDE_FILTER = "hide_product_search_top_filter_recylerView";

    //产品搜索页面底部筛选结果
    public static final String FILTER_RESULT = "product_search_filter_result";


    //筛选界面获取产品界面传过来的每个item  list列表数据(如出发城市等.)
    public static final String FILTER_ACTIVITY_DATA = "filter_activity_list_data";

    // 微信支付取消后h5页面后退
    public static final String WX_PAY_H5_GO_BACK = "WX_PAY_H5_GO_BACK";

    //微信支付成功后跳转支付成功页面
    public static final String WX_PAY_H5_SUCCESS = "WX_PAY_H5_SUCCESS";

    //推送跳转到第四个界面
    public static final String CHANGE_MAIN_TAB4 = "change_main_tab4";


    /**
     * 筛选日期中StartDateActivity  关闭页面
     */
    public static final String FINISH_DATE_ACTIVITY = "finish_startDateActivity";
    //筛选界面获取产品界面传过来的每个item  list列表数据(如出发城市等.)
    //sortActivity选中传过来的值到ProductSearchActiivty
    public static final String SORT_ACTIVITY_TOA1 = "sort_activity_to_product_search_activity";
    //sortActivity选中传过来的值到ProductFliterActivity
    public static final String SORT_ACTIVITY_TOA2 = "sort_activity_to_product_filter_activity";
    //接收筛选后的请求参数
    //public static final String REQUEST_FILTER_RESUALT="request_filter_resualt";
    //清空筛选操作
    public static final String FILTER_CLEAN = "filter_clean";


    /**
     * 邮轮日历
     */
    //接收邮轮日历传递过来的date数据集合
    public static final String CRUISESHIP_DATE = "cruiseship_date";


    public static final String SCROLLPAGE_UPDATE = "scrollpage_update";

    /**
     * 出发口岸改变
     */
    public static final String START_CITY_UPDATE = "START_CITY_UPDATE";

    /**
     * 机票url
     */
    public static final String AIR_TICKET = "air_ticket";

    /**
     * 首页卡片删除
     */
    public static final String CARD_DELETE = "CARD_DELETE";


    //筛选后增加一个参数
    public static final String SEARCH_OFF = "search_off";


    /**
     * 关键词搜索
     */
    public static final String SEARCH_TEXT_CHANGE = "SEARCH_TEXT_CHANGE";


    /**
     * 设置页面实时更新微信认证状态
     */
    public static final String WX_AUTHORITY_STATUS_CHANGE = "WX_AUTHORITY_STATUS_CHANGE";



    //public static final String FINISH_LOGIN_SKBPHONE_ACTIVITY = "finish_login_skbphone_activity";
}
