package com.myCompany.conference.service.impl;

import com.myCompany.conference.Constants;
import com.myCompany.conference.model.AbstractModel;
import com.myCompany.conference.service.NotificationService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AsyncEmailNotificationService extends NotificationService {
    private static final Logger LOGGER = Logger.getLogger(AsyncEmailNotificationService.class);
    private final ServiceManager serviceManager;
    private final ExecutorService executorService;
    private final int tryCount;

    public AsyncEmailNotificationService(ServiceManager serviceManager) {
        super();
        this.serviceManager = serviceManager;
        this.executorService = Executors.newCachedThreadPool();
        tryCount = Constants.TRY_COUNT;
    }

    @Override
    public void sendNotification(String fromEmail, String toEmail, String name,  String phone, String message) {
        executorService.submit(new EmailItem(fromEmail, toEmail, message + " /n " + name + " ( " + phone + " )", tryCount));
    }

    @Override
    public void shutdown() {
        executorService.shutdown();
    }

    private class EmailItem extends AbstractModel implements Runnable{
        private final String fromEmail;
        private final String toEmail;
        private final String name;
        private final String phone;
        private final String message;
        private int tryCount;

        public EmailItem(String fromEmail, String toEmail, String name, String phone, String message, int tryCount) {
            super();
            this.fromEmail = fromEmail;
            this.toEmail = toEmail;
            this.name = name;
            this.phone = phone;
            this.message = message;
            this.tryCount = tryCount;
        }

        private boolean isValidTryCount(){
            return tryCount > 0;
        }

        @Override
        public void run() {
            String notificationEmail = toEmail;
            SimpleEmail email = new SimpleEmail();
            email.setCharset("utf-8");
            email.setHostName(Constants.SERVER);
            email.setSSLOnConnect(true);
            email.setSslSmtpPort(Constants.PORT);
            email.setAuthenticator(new DefaultAuthenticator());
        }
    }
}
