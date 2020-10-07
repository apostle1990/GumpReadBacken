package com.gumpread.constants;

/**
 * 一些公共常量，这里主要是配置一些验证码id头，每个用户登陆之后的sessionid头，等存储在redis里面的信息；
 */
public class PublicConstants {
    public static final String CAPTCHA_KEY = "captcha_key:";
    public static final long CAPTCHA_EXPIRE = 60;
    public static final String TOKEN_PREFIX = "userToken_";
}
