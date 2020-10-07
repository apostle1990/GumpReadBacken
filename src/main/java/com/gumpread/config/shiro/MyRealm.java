package com.gumpread.config.shiro;

import com.gumpread.domain.entity.User;
import com.gumpread.service.user.impl.SignService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 具体的登陆验证以及授权认证；
 */
public class MyRealm extends AuthorizingRealm {


    private static final Logger log = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    SignService signService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = (String) usernamePasswordToken.getPrincipal();
        User user = signService.getUserByUserName(userName);
        log.info(String.format("doGetAuthenticationInfo认证阶段成功，数据库查到的user：%s",user));
        if(user == null){
            throw new UnknownAccountException("无此用户名");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                user.getUserName(),
                user.getPassword(),
                getName()
        );
        Subject subject = SecurityUtils.getSubject();
        log.info(String.format("认证成功，user已经放到session,sessionId: %s",subject.getSession().getId()));
        subject.getSession().setAttribute("user", user);
        return simpleAuthenticationInfo;
    }
}
