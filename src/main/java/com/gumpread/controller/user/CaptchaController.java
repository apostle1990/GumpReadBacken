package com.gumpread.controller.user;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.gumpread.constants.PublicConstants;
import com.gumpread.utils.AjaxResult;
import com.gumpread.utils.IdUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Controller
public class CaptchaController {
    private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);

    @Resource(name = "captchaProducer")
    DefaultKaptcha defaultKaptcha;

    @Resource(name = "redisTemplate")
    RedisTemplate redisTemplate;

    @GetMapping("/getCaptchaImg")
    @ResponseBody
    public AjaxResult getCaptchaImg(){
//        产生验证码
        String code = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(code);
//        获得验证码id
        String captchaId = IdUtils.getSimpleUUID();
        String captchaCodeKey = PublicConstants.CAPTCHA_KEY+captchaId;
        redisTemplate.opsForValue().set(captchaCodeKey, code, PublicConstants.CAPTCHA_EXPIRE, TimeUnit.SECONDS);


        FastByteArrayOutputStream fastByteArrayOutputStream = new FastByteArrayOutputStream();
        try {
            boolean jpg = ImageIO.write(image, "jpg", fastByteArrayOutputStream);
            if(!jpg){
                return AjaxResult.error();
            }
        } catch (IOException e) {
            return AjaxResult.error();
        }
        AjaxResult successAjax = AjaxResult.success();
        successAjax.put("captchaImg", Base64.encode(fastByteArrayOutputStream.toByteArray()));
        successAjax.put("captchaId", captchaId);

        log.info(String.format("返回验证码base64编码, redis存放验证码id成功,code和key分别为：%s,  %s",code,captchaCodeKey));
        return successAjax;
    }
}
