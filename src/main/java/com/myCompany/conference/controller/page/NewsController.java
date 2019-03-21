package com.myCompany.conference.controller.page;

import com.myCompany.conference.Constants;
import com.myCompany.conference.controller.AbstractController;
import com.myCompany.conference.entity.Conference;
import com.myCompany.conference.model.Items;
import com.myCompany.conference.model.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/news", "/news/*"})
public class NewsController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = getOffset(req, Constants.LIMIT_CONFERENCE_PER_PAGE);
        String requestUrl = req.getRequestURI();
        Items<Conference> items = null;
        items = getBusinessService().listConference(offset, Constants.LIMIT_CONFERENCE_PER_PAGE);
        req.setAttribute("list", items.getItems());
        Pagination pagination = new Pagination.Builder(requestUrl + "?", offset, items.getCount()).withLimit(Constants.LIMIT_CONFERENCE_PER_PAGE).build();
        req.setAttribute("pagination", pagination);
        forwardToPage("news.jsp", req, resp);
    }
}
