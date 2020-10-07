package com.gumpread.exception.user;

import com.gumpread.exception.BaseException;

public class UserExcpetion extends BaseException {

    public UserExcpetion(String code, Object[] args, String message) {
        super("user", code, args, null);
    }
}
