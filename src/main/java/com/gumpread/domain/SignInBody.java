package com.gumpread.domain;



public class SignInBody {
    private String userName;
    private String password;
    private String captcha;
    private String captchaId;


    @Override
    public String toString() {
        return "SignInBody{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", captcha='" + captcha + '\'' +
                ", captchaId='" + captchaId + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }
}
