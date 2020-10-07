package com.gumpread.config.shiro;

import com.gumpread.constants.PublicConstants;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager {
    private static final Logger log = LoggerFactory.getLogger(MySessionManager.class);

    @Value("${token.tokenHeader}")
    private String tokenHeader;

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public MySessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String receivedToken = httpServletRequest.getHeader(tokenHeader);
        log.info(String.format("后端收到的tokenHeader, %s", receivedToken));
        if(receivedToken != null && receivedToken.startsWith(PublicConstants.TOKEN_PREFIX)){
            receivedToken =  receivedToken.replace(PublicConstants.TOKEN_PREFIX, "");

            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, receivedToken);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            log.info(String.format("从请求头里面取出token作为sessionId， %s", receivedToken));
            return receivedToken;
        }else {
            log.info(String.format("返回的sessionId,不是从请求头里面拿出来的， %s", super.getSessionId(request, response)));
            return super.getSessionId(request, response);
        }
    }
}
