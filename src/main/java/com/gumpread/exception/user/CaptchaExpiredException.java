package com.gumpread.exception.user;

public class CaptchaExpiredException extends UserExcpetion{
    public CaptchaExpiredException(){
        this(null);
    }

    public CaptchaExpiredException(Object[] args) {
        super("user.captcha.error", args, "验证码已过期");
    }
}
