package com.gumpread.service.user.impl;


import com.gumpread.constants.PublicConstants;
import com.gumpread.domain.entity.User;
import com.gumpread.exception.user.CaptchaExpiredException;
import com.gumpread.exception.user.CaptchaNotEqualException;
import com.gumpread.mapper.UserMapper;
import com.gumpread.service.user.ISignService;
import com.gumpread.utils.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SignService implements ISignService {

    private static final Logger log = LoggerFactory.getLogger(SignService.class);

    @Resource(name = "redisTemplate")
    RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;

    public AjaxResult authSignIn(String userName, String password, String userCaptchaCode, String captchaId) {
        String sysCaptchaCode = (String) redisTemplate.opsForValue().get(PublicConstants.CAPTCHA_KEY + captchaId);
        redisTemplate.delete(PublicConstants.CAPTCHA_KEY+captchaId);
//        验证码过期
        if(sysCaptchaCode == null){
            log.info("验证码过期");
            throw new CaptchaExpiredException();
        }
//        验证码错误
        if(! sysCaptchaCode.equals(userCaptchaCode)){
            log.info("验证码错误");
            throw new CaptchaNotEqualException();
        }
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        if(!subject.isAuthenticated()){
            try{
                subject.login(usernamePasswordToken);

            }catch (UnknownAccountException e){
                return AjaxResult.error("无此用户名");
            }catch (IncorrectCredentialsException e){
                return AjaxResult.error("密码错误");
            }catch (Exception e) {
                return AjaxResult.error("系统异常");
            }
        }
        String token = (String) subject.getSession().getId();
        AjaxResult ajaxResult = AjaxResult.success();
        Object principal = subject.getSession().getAttribute("user");
//        Object principal = subject.getPrincipal();
        ajaxResult.put("token", token);
        ajaxResult.put("user", principal);
        return ajaxResult;

    }

    public User getUserByUserName(String userName){
        log.info(String.format("getUserByUserName数据库查询参数：%s",userName));
        return userMapper.getUserByUserName(userName);
    }

}
