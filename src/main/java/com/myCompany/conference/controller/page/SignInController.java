package com.myCompany.conference.controller.page;

import com.myCompany.conference.controller.AbstractController;
import com.myCompany.conference.entity.User;
import com.myCompany.conference.exception.ApplicationException;
import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.form.SignInForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage("signIn.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SignInForm form = createForm(req, SignInForm.class);
            User user = getBusinessService().signIn(form);
            if (user == null) {
                resp.sendRedirect("/404?url= user");
            } else {
                setSession(req.getSession());
                getSession().setAttribute("user", user);
                resp.sendRedirect("/role");
            }
        }catch (ValidateException e){
            throw new ApplicationException("Validation should be done on client side: " + e.getMessage(), e);
        }
    }
}
