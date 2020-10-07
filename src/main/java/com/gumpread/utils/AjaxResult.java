package com.gumpread.utils;

import com.gumpread.constants.HttpStatus;

import java.util.HashMap;

/**
 * 对所有请求的处理，需要将所有响应数据，封装到此对象里面，进行返回；
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final String  CODE_TAG = "code";
    private static final String  MSG_TAG = "msg";
    private static final String  DATA_TAG = "data";

    private static final String SUCCESS_MSG = "操作成功";
    private static final String ERROR_MSG = "操作失败";


    private AjaxResult(){

    }
    private AjaxResult(int code, String msg){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    private AjaxResult(int code, String msg, Object data){
        this(code, msg);
        if(data != null){
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 操作成功返回的信息，默认成功状态码为http状态码200
     * @return
     */
    public static AjaxResult success(){
        return AjaxResult.success(SUCCESS_MSG);
    }

    public static AjaxResult success(String msg){
        return success(msg, null);
    }
    public static AjaxResult success(Object data){
        return success(SUCCESS_MSG, data);
    }

    public static AjaxResult success(String msg, Object data){
        return success(HttpStatus.SUCCESS, msg, data);
    }

    public static AjaxResult success(int code, String msg, Object data){
        return new AjaxResult(code, msg, data);
    }

    public static AjaxResult success(int code){
        return success(code, SUCCESS_MSG);
    }

    public static AjaxResult success(int code, String msg){
        return success(code, msg, null);
    }

    public static AjaxResult success(int code, Object data){
        return success(code, SUCCESS_MSG, data);
    }


    /**
     * 操作失败返回的信息，默认的失败状态码是500
     * @return
     */
    public static AjaxResult error(){
        return AjaxResult.error(ERROR_MSG);
    }

    public static AjaxResult error(String msg){
        return AjaxResult.error(msg, null);
    }
    public static AjaxResult error(Object data){
        return error(ERROR_MSG, data);
    }

    public static AjaxResult error(String msg, Object data){
        return AjaxResult.error(HttpStatus.ERROR, msg, data);
    }

    public static AjaxResult error(int code, String msg, Object data){
        return new AjaxResult(code, msg, data);
    }

    public static AjaxResult error(int code, String msg){
        return error(code, msg, null);
    }
    public static AjaxResult error(int code){
        return error(code, ERROR_MSG);
    }
    public static AjaxResult error(int code, Object data){
        return error(code, ERROR_MSG, data);
    }
}
