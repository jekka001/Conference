package com.myCompany.conference.controller.page;

import com.myCompany.conference.controller.AbstractController;
import com.myCompany.conference.entity.Speaker;
import com.myCompany.conference.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/role")
public class RoleController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage("role.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        setSession(req.getSession());

        if(getSession().getAttribute("user") != null) {
            if(role.equals("Speaker")){
                Speaker speaker = getBusinessService().getSpeakerDate((User)getSession().getAttribute("user"));
                getSession().setAttribute("speaker", speaker);
            }
            getSession().setAttribute("currentRole", role);
            resp.sendRedirect("/news");
        }else {
            resp.sendRedirect("/signIn");
        }
    }
}
