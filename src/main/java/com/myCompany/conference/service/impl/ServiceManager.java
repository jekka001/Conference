package com.myCompany.conference.service.impl;

import com.myCompany.conference.dao.connection.ConnectionPoolHolder;
import com.myCompany.conference.service.BusinessService;
import org.apache.log4j.Logger;
import org.omg.IOP.ServiceContext;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.SQLException;

public class ServiceManager {
    private static final String SERVICE_MANAGER = "SERVICE_MANAGER";
    private static final Logger LOGGER = Logger.getLogger(ServiceManager.class);
    final BusinessService businessService;
    final Connection connection;

    public BusinessService getBusinessService()  {
        return businessService;
    }

    public ServiceManager(ServletContext context) {
        businessService = new BusinessServiceImpl();
        LOGGER.info("ServiceManager instance created");
        connection = ConnectionPoolHolder.getInstance().getConnection();
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
