package com.myCompany.conference.listener;

import com.myCompany.conference.entity.Conference;
import com.myCompany.conference.service.impl.ServiceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceManager serviceManager = ServiceManager.getInstance(sce.getServletContext());
        List<Conference> list = serviceManager.getBusinessService().listConference();
        sce.getServletContext().setAttribute("conference", list);
        LOGGER.info("Application started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServiceManager.getInstance(sce.getServletContext()).destroy();
        LOGGER.info("Application destroyed");
    }
}
