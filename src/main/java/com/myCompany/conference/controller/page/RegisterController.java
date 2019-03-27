package com.myCompany.conference.controller.page;

import com.myCompany.conference.controller.AbstractController;
import com.myCompany.conference.entity.User;
import com.myCompany.conference.exception.ApplicationException;
import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.form.RegisteredForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage("register.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RegisteredForm form = createForm(req, RegisteredForm.class);
            User user = getBusinessService().createUser(form);
                setSession(req.getSession());
                getSession().setAttribute("user", user);
                resp.sendRedirect("/role");
        }catch (ValidateException e){
            throw new ApplicationException("Validation should be done on client side: " + e.getMessage(), e);
        }
    }
}
