package com.gumpread.config.shiro;


import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * 权限管理框架shiro的配置
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

//    private int timeout = 10;

    @Bean
    public MyRealm getMyRealm(){
        MyRealm realm = new MyRealm();
        realm.setCachingEnabled(true);
        return realm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(getMyRealm());
        return securityManager;
    }

    /**
     * 配置请求的权限：
     *      anon: 匿名
     *      authc: 需要登陆
     *      unauthorized : 用户无权限自动跳转；
     *      unSignIn: 未登录跳转；
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactorBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, String> filterChain = new LinkedHashMap<>();
        filterChain.put("/getCaptchaImg", "anon");
        filterChain.put("/signIn", "anon");
        filterChain.put("/signOut", "anon");
        filterChain.put("/test", "anon");
        filterChain.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChain);
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setLoginUrl("/unSignIn");
        return shiroFilterFactoryBean;
    }


    /**
     * 讲session存储到redis里面，而不是本地；
     */
    @Bean
    public SessionManager sessionManager(){
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPassword(password);
        redisManager.setPort(port);
//        redisManager.setTimeout(timeout);
        redisManager.setExpire(1800);
        return redisManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
}
