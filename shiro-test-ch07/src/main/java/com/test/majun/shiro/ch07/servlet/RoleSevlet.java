package com.test.majun.shiro.ch07.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by majun on 23/12/2017.
 */
@WebServlet(name = "roleServlet", urlPatterns = "/role")
public class RoleSevlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();

        subject.checkRole("admin");
        req.setAttribute("subject",subject);
        req.getRequestDispatcher("/WEB-INF/jsp/hasRole.jsp").forward(req, resp);
    }

}
