package com.gumpread.config;


import com.gumpread.utils.tranlsate.TransApi;
import org.springframework.stereotype.Component;

@Component(value = "translate")
public class Translate {
    private static final String appId = "20200824000550702";
    private static final String securityKey = "cY8k2tuOUTZKVJWwPt9V";
    private static final TransApi transApi = new  TransApi(appId, securityKey);;

    public String translateToChinese(String query){
        String transResult = transApi.getTransResult(query, "auto", "zh");
        return transResult;
    }
}
