package com.myCompany.conference.controller;

import com.myCompany.conference.form.AbstractForm;
import com.myCompany.conference.service.BusinessService;
import com.myCompany.conference.service.impl.ServiceManager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractController extends HttpServlet {
    protected final Logger LOGGER = Logger.getLogger(AbstractController.class);
    private BusinessService businessService;
    private HttpSession session;

    public HttpSession getSession() {
        return session;
    }
    public void setSession(HttpSession session) {
        this.session = session;
    }

    public final BusinessService getBusinessService() {
        return businessService;
    }
    public final int getOffset(HttpServletRequest req, int limit){
        String val = req.getParameter("page");
        if(val != null){
            int page = Integer.parseInt(val);
            return (page - 1) * limit;
        }else{
            return 0;
        }
    }

    @Override
    public void init() throws ServletException {
        businessService = ServiceManager.getInstance(getServletContext()).getBusinessService();
    }

    public final void forwardToPage(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "page/" + jspPage);
        req.getRequestDispatcher("/WEB-INF/JSP/pageTemplate.jsp").forward(req, resp);
    }

    public final void forwardToFragment(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspPage).forward(req, resp);
    }

    public final <T extends AbstractForm> T createForm(HttpServletRequest req, Class<T> formClass) throws ServletException{
        try{
            T form = formClass.newInstance();
            form.setLocale(req.getLocale());
            BeanUtils.populate(form, req.getParameterMap());
            return form;
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new ServletException(e);
        }
    }

}
