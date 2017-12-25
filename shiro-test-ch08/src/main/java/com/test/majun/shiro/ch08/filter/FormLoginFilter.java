package com.test.majun.shiro.ch08.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by majun on 25/12/2017.
 */
public class FormLoginFilter extends PathMatchingFilter {

    private String loginUrl="/login.jsp";

    private String successUrl="/";

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        if(SecurityUtils.getSubject().isAuthenticated()){
            return true; //已经登录过
        }

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;

        if(isLoginRequest(req)){
            if("post".equalsIgnoreCase(req.getMethod())){
                if(login(req)){
                    //登录成功,跳转至登录成功页
                    return redirectToSuccessUrl(req,resp);
                }
            }
            return true; //继续过滤器连
        }else{
            //保存当前地址并重定向到登录页
            saveRequestAndRedirectToLogin(req,resp);
            return false;
        }
    }

    private boolean isLoginRequest(HttpServletRequest req){
        return pathsMatch(loginUrl, WebUtils.getPathWithinApplication(req));
    }

    private boolean login(HttpServletRequest req){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try{
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username,password));
        }catch (Exception e){
            req.setAttribute("shiroLoginFailure", e.getClass());
            return false;
        }
        return true;
    }

    private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.redirectToSavedRequest(req,resp,successUrl);
        return false;
    }

    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.saveRequest(req);
        WebUtils.issueRedirect(req,resp,loginUrl);
    }


}
