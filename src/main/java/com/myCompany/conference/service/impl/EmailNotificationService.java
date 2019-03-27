package com.myCompany.conference.service.impl;

import com.myCompany.conference.Constants;
import com.myCompany.conference.service.NotificationService;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;


public class EmailNotificationService implements NotificationService {
    private static final Logger LOGGER = Logger.getLogger(EmailNotificationService.class);
    private int tryCount;

    public EmailNotificationService() {
        super();
        tryCount = Constants.TRY_COUNT;
    }

    private boolean isValidTryCount(){
        return tryCount > 0;
    }
    @Override
    public void sendNotification(String title, String content, String toEmail, String fromEmail) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(Constants.SERVER);
            email.setSmtpPort(Constants.PORT);
            email.setAuthenticator(new DefaultAuthenticator(Constants.EMAIL_SITE, "*"));
            email.setSSLOnConnect(true);
            email.setFrom(fromEmail);
            email.setSubject(title);
            email.setMsg(content);
            email.addTo(toEmail);
            email.send();
        }catch (EmailException e) {
            LOGGER.error("Can't send email: " + e.getMessage(), e);
            tryCount--;
            if (isValidTryCount()) {
                LOGGER.info("Resend email: {}"  + this.toString());
                sendNotification(title, content, toEmail, fromEmail);
            } else {
                LOGGER.error("Email was not sent: limit of try count");
            }
        }catch (Exception e){
            LOGGER.error("Error during send email: " + e.getMessage(), e);
        }
    }

}
