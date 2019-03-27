package com.myCompany.conference.service.impl;

import com.myCompany.conference.dao.connection.ConnectionPoolHolder;
import com.myCompany.conference.service.BusinessService;
import com.myCompany.conference.service.EncryptionService;
import com.myCompany.conference.service.I18nService;
import com.myCompany.conference.service.NotificationService;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.SQLException;

public class ServiceManager {
    private static final String SERVICE_MANAGER = "SERVICE_MANAGER";
    private static final Logger LOGGER = Logger.getLogger(ServiceManager.class);
    final BusinessService businessService;
    final Connection connection;
    final NotificationService notificationService;
    final EncryptionService encryptionService;
    final I18nService i18nService;

    public BusinessService getBusinessService()  {
        return businessService;
    }

    private ServiceManager(ServletContext context) {
        connection = ConnectionPoolHolder.getInstance().getConnection();
        notificationService = new EmailNotificationService();
        encryptionService = EncryptionServiceImpl.instance;
        i18nService = new I18nServiceImpl();
        businessService = new BusinessServiceImpl(this);
        LOGGER.info("ServiceManager instance created");
    }

    public static ServiceManager getInstance(ServletContext context){
        ServiceManager instance = (ServiceManager) context.getAttribute(SERVICE_MANAGER);
        if(instance == null){
            instance = new ServiceManager(context);
            context.setAttribute(SERVICE_MANAGER, instance);
        }
        return instance;
    }

    public void destroy(){
        try{
            connection.close();
        }catch (SQLException e){
            LOGGER.error("Close Connection failed: " + e.getMessage(), e);
        }
        LOGGER.info("ServiceManager instance destroyed");
    }
}
