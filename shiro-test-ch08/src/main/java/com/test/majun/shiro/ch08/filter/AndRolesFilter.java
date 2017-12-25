package com.test.majun.shiro.ch08.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by majun on 25/12/2017.
 */
public class AndRolesFilter extends AccessControlFilter {

    private String unauthorizedUrl;
    private String loginUrl = "/login.jsp";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {
        String[] roles = (String[])mappedValue;
        if(roles == null){
            return true;//如果没有设置角色参数,默认成功
        }
        for(String role : roles){
            if(getSubject(servletRequest,servletResponse).hasRole(role)){
                return true;
            }
        }
        return false; //跳到onAccessDenied处理
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest,servletResponse);
        if(!subject.isAuthenticated()){
            //表示没有登录，重定向到登录页面
            WebUtils.issueRedirect(servletRequest,servletResponse,loginUrl);
        }else{
            if(StringUtils.hasText(unauthorizedUrl)){
                //如果有未授权页面跳转过去
                servletRequest.setAttribute("subject",subject);
                WebUtils.issueRedirect(servletRequest,servletResponse,unauthorizedUrl);
            }else{
                //否则返回401未授权状态码
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
        return false;
    }

    public void setUnauthorizedUrl(String unauthorizedUrl) {
        this.unauthorizedUrl = unauthorizedUrl;
    }

    @Override
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
