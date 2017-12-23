package com.test.majun.shiro.ch07.servlet;

import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by majun on 23/12/2017.
 */
@WebServlet(name = "formfilterloginServlet", urlPatterns = "/formfilterlogin")
public class FormFilterLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = (String)req.getAttribute("shiroLoginFailure");

        if(UnknownAccountException.class.getName().equals(error)){
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(error)){
            error = "用户名/密码错误";
        } else if(!StringUtils.isEmpty(error)){
            error = "未知错误:"+error;
        }
        req.setAttribute("error",error);
        req.getRequestDispatcher("/WEB-INF/jsp/formfilterlogin.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
