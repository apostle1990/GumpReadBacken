package com.gumpread.controller.user;

import com.gumpread.constants.HttpStatus;
import com.gumpread.service.user.ISignService;
import com.gumpread.utils.AjaxResult;
import com.gumpread.domain.SignInBody;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 处理登陆登出相关请求
 */


@RestController
public class SignController {
    @Autowired
    ISignService signService;

    private static final Logger log = LoggerFactory.getLogger(SignController.class);



    @PostMapping(value = "/signIn")
    public AjaxResult signIn(@RequestBody SignInBody requestBody){
        log.info(String.format("收到登录请求signIn: %s", requestBody));
        String userName = requestBody.getUserName();
        String password = requestBody.getPassword();
        String userCaptchaCode = requestBody.getCaptcha();
        String captchaId = requestBody.getCaptchaId();

        return signService.authSignIn(userName, password, userCaptchaCode, captchaId);
    }


    @PostMapping(value = "/signOut")
    public AjaxResult signOut(){
        log.info("收到登出请求signOut");
        SecurityUtils.getSubject().logout();
        return AjaxResult.success("登出成功");
    }

    @RequestMapping("/unSignIn")
    public AjaxResult unSignIn(){
        log.info("收到未登录unSign请求");
        AjaxResult ajaxResult = AjaxResult.error(HttpStatus.UNAUTHORIZED, "未登录");
        return ajaxResult;
    }

    @RequestMapping("/unauthorized")
    public AjaxResult unauthorized(){
        log.info("收到未授权请求unauthorized");
        AjaxResult ajaxResult = AjaxResult.error(HttpStatus.FORBIDDEN, "未授权");
        return ajaxResult;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public AjaxResult testGet(@RequestParam("name") String name, @RequestParam("age") int age){
        log.info("收到测试请求test: "+name+"\tage: "+age);
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("message","登陆成功");
        return ajaxResult;
    }
}
