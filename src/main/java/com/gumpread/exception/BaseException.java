package com.gumpread.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
//    异常所属模块
    private String module;

    private String code;

//    异常对应参数
    private Object[] args;
//    异常信息
    private String message = "no further infomation";


    public BaseException(String module, String code, Object[] args, String message){
        this.module = module;
        this.code = code;
        this.args = args;
        if(message != null){
            this.message = message;
        }
    }

    public BaseException(String module, String code, Object[] args)
    {
        this(module, code, args, null);
    }

    public BaseException(String module, String defaultMessage)
    {
        this(module, null, null, defaultMessage);
    }

    public BaseException(String code, Object[] args)
    {
        this(null, code, args, null);
    }

    public BaseException(String defaultMessage)
    {
        this(null, null, null, defaultMessage);
    }

}
