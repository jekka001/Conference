package com.myCompany.conference.controller.page;

import com.myCompany.conference.controller.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logOut")
public class LogOutController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setSession(req.getSession());
        getSession().invalidate();
        resp.sendRedirect("/");
    }
}
