package com.deng.commons.enums;

import lombok.Getter;

/**
 * 返回结果的枚举类
 */
@Getter
public enum ResultEnum {

    //通用返回码
    SUCCESS(0, "SUCCESS"),
    ERR(1, "ERR"),

    //商品校验返回码
    COMMODITY_DOWN(10000,"商品已下架"),
    INVENTORY_UPDATE(10001,"规格已修改，请重新选择"),
    INVENTORY_NUM_NOT_ENOUGH(10002,"库存不足"),
    COMMODITY_SAME(10006,"您所在的县级公司已有同类商品，不可重复上传"),

    //订单校验返回码
    ORDER_IS_PAY(10003,"该订单已支付,请勿重复支付"),
    ORDER_LIST_IS_NULL(10004,"订单列表为空"),
    ORDER_MONEY_DIFF(10005,"前后端金额不一致"),
    ORDER_NUMBER_IS_NULL(10008,"订单号不存在"),

    //购物车返回码
    SC_NUM_ENOUGH(10007,"购物车单商品最大数量999"),

    //通用增删改查返回码
    ADD_FAIL(1001,"新增失败,请联系管理员"),
    UPDATE_FAIL(1002,"更新失败,请联系管理员"),
    SELECT_FAIL(1003,"查询失败,请联系管理员"),
    DELETE_FAIL(1003,"删除失败,请联系管理员"),

    //快递100返回码
    POST_QUERY_FAIL(101,"快递查询失败,请联系管理员"),


    NOT_NETWORK(-1, "系统繁忙，请稍后再试。"),
    LOGIN_VERIFY_FALL(2, "登录信息已失效"),
    PARAM_VERIFY_FALL(3, "参数验证错误"),
    AUTH_FAILED(4, "权限验证失败"),
    DATA_NOT(5, "没有相关数据"),
    DATA_CHANGE(6, "数据没有任何更改"),
    DATA_REPEAT(7, "数据已存在"),
    PRE_ADMIN(8, "请先删除对应的管理员"),
    NOT_RESOURCES(9,"资源路径不存在"),
    NO_INVENTORY(10,"库存不足"),
    SETTLEED_YES(11,"已经入住"),
    SETTLEED_NO(12,"尚未入住"),
    UNSUPPORT_FILEFORMAT_CODE(407,"不支持的文件格式"),
    USERNAMEORPWD_ERROR_CODE(501,"用户名或密码错误"),
    REPEAT_LOGIN_CODE(602,"账号已在其他设备登录"),
    NOSUCH_JURISDICTION(607,"无权操作"),
    ACCOUNT_FROZEN_CODE(609,"账号被冻结"),
    EXECUTION_FAILURE_MESSAGE(610,"操作失败"),
    LARGE_WIDTH(800,""),
    LARGE_HEIGHT(800,""),
    MEDIUM_WIDTH(500,""),
    MEDIUM_HEIGHT(500,""),
    SMALL_WIDTH(200,""),
    SMALL_HEIGHT(200,""),

    //生成明细
    CREATE_DETAIL_ERROR(1,"生成明细失败"),

    FAIL(500,"system error"),
    INIT(600,"record init"),
    TASKNO_EXIST(1001,"该任务key值已经存在"),
    PARAM_EMPTY(6001,"parameter is empty"),
    FROZEN(10001,"FROZEN"),
    UNFROZEN(10002,"UNFROZEN"),
    RUN_NOW_FAIL(7001,"立即运行失败"),
    HTTP(10003,"http"),
    KAFKA(10004,"kafka"),
    NO_DATA(1003,"无此定时任务编号");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
