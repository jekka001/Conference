package com.myCompany.conference.controller.page;

import com.myCompany.conference.controller.AbstractController;
import com.myCompany.conference.entity.Conference;
import com.myCompany.conference.entity.Review;
import com.myCompany.conference.entity.User;
import com.myCompany.conference.exception.ApplicationException;
import com.myCompany.conference.exception.ValidateException;
import com.myCompany.conference.form.ConferenceForm;
import com.myCompany.conference.model.Items;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

@WebServlet("/conference")
public class ConferenceController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setSession(req.getSession());
        long idConference;
        Timestamp timeConference;
        if(req.getParameter("idConference") != null) {
            idConference = Long.valueOf(req.getParameter("idConference"));
            getSession().setAttribute("idConference", idConference);
            getSession().setAttribute("title", req.getParameter("title"));
            timeConference = Timestamp.valueOf(req.getParameter("timeConduction"));
            if(timeConference.before(new Timestamp(System.currentTimeMillis()))) {
                getSession().setAttribute("timeOver", true);
            }else{
                getSession().setAttribute("timeOver", false);
            }
        }else{
            idConference = (Long)getSession().getAttribute("idConference");
        }
        Items<Review> items;
        items = getBusinessService().listReview(idConference);
        setSession(req.getSession());
        getSession().setAttribute("listItems", items.getItems());
        forwardToPage("conference.jsp", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ConferenceForm form = createForm(req, ConferenceForm.class);
            getBusinessService().uploadConference(form);
        } catch (ValidateException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/news");
    }
}
