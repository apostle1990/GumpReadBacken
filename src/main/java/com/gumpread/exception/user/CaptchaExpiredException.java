package com.gumpread.exception.user;

/**
 * 登陆的时候，输入的验证码过期异常
 */
public class CaptchaExpiredException extends UserExcpetion{
    public CaptchaExpiredException(){
        this(null);
    }

    public CaptchaExpiredException(Object[] args) {
        super("user.captcha.error", args, "验证码已过期");
    }
}
