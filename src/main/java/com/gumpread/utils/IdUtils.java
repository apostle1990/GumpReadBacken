package com.gumpread.utils;

import java.util.UUID;

public class IdUtils {

    public static String getSimpleUUID(){
        return UUID.randomUUID().toString();
    }
}
