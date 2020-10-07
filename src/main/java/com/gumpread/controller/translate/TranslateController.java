package com.gumpread.controller.translate;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gumpread.config.Translate;
import com.gumpread.controller.user.CaptchaController;
import com.gumpread.domain.translate.TranslateResult;
import com.gumpread.utils.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 接收翻译相关的请求；
 */
@RestController
@RequestMapping("/translate")
public class TranslateController {
    private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);

    @Resource(name = "translate")
    private Translate translate;


    @RequestMapping(path = {"/toEng"}, method = RequestMethod.POST)
    public AjaxResult translateToEn(@RequestBody String text){
        log.info(String.format("/translate/toEng收到翻译请求,需要翻译的文字为:\n %s", text));
        String translateResoult = translate.translateToChinese(text);
        log.info(String.format("/translate/toEng翻译结果为:\n %s", translateResoult));

        TranslateResult result = JSON.parseObject(translateResoult, new TypeReference<TranslateResult>() {
        });

        return AjaxResult.success(result);
    }
}
