package com.myCompany.conference.controller.page;

import com.myCompany.conference.controller.AbstractController;
import com.myCompany.conference.entity.Speaker;
import com.myCompany.conference.exception.ApplicationException;
import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.form.ReviewAdminForm;
import com.myCompany.conference.form.ReviewForm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/review")
public class ReviewController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long idUser = Long.valueOf(req.getParameter("idUser"));
        long idReview = Long.valueOf(req.getParameter("idReview"));
        getBusinessService().uploadRegisterReview(idReview, idUser);
        resp.sendRedirect("/conference");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setSession(req.getSession());

        try {
            if(getSession().getAttribute("speaker") != null) {
                Speaker speaker = (Speaker) getSession().getAttribute("speaker");
                ReviewForm form = createForm(req, ReviewForm.class);
                form.setSpeakerName(speaker);
                getBusinessService().createReview(form, (Long)req.getSession().getAttribute("idConference"));
            }else {
                ReviewAdminForm form = createForm(req, ReviewAdminForm.class);
                getBusinessService().uploadReview(form);
            }
        }catch (ValidateException e) {
            throw new ApplicationException("Validation should be done on client side: " + e.getMessage(), e);
        }
        resp.sendRedirect("/conference");
    }
}
