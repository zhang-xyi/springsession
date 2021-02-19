package com.paparazzi.springsession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: springsession
 * @Package: com.paparazzi.springsession
 * @Description: java类作用描述
 * @Author: 张子凡
 * @CreateDate: 2021/2/19 20:46
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2021
 */
@WebServlet(urlPatterns = "/setsession")
public class SetSession extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("session", "session Object");
        resp.getWriter().write("set session success");
    }
}
