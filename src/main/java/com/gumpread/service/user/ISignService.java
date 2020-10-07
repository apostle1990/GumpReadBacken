package com.gumpread.service.user;

import com.gumpread.domain.entity.User;
import com.gumpread.utils.AjaxResult;

public interface ISignService {
    public AjaxResult authSignIn(String userName, String password, String userCaptchaCode, String captchaId);
    public User getUserByUserName(String userName);
}
